package com.codebase.api

import android.content.Context
import com.google.gson.GsonBuilder
import com.codebase.constants.ApiConstants
import com.codebase.utils.PreferenceManager
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
class NetModule(var context: Context) {

    var mPrefs: PreferenceManager
    lateinit var retrofit: Retrofit

    init {
        mPrefs = PreferenceManager(context)
    }

    //get retrofit instance
    val restService: RestService
        @Singleton
        @Provides
        get() {

            val gson = GsonBuilder()
                .setLenient()
                .create()

            retrofit = Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(unsafeOkHttpClient)
                .build()

            return retrofit.create<RestService>(RestService::class.java)
        }

    // Create a trust manager that does not validate certificate chains
    // Install the all-trusting trust manager
    // Create an ssl socket factory with our all-trusting manager
    val unsafeOkHttpClient: OkHttpClient
        get() {
            try {
                val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf()
                    }
                })
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, java.security.SecureRandom())
                val sslSocketFactory = sslContext.socketFactory

                val builder = OkHttpClient.Builder()
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.interceptors().add(httpLoggingInterceptor)
                builder.addInterceptor(getHeaderForApiCalls())
                builder.readTimeout(120, TimeUnit.SECONDS)
                builder.connectTimeout(120, TimeUnit.SECONDS)
                builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                builder.hostnameVerifier { hostname, session -> true }

                return builder.build()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }

        }

    private fun getHeaderForApiCalls() : Interceptor{
      return Interceptor { chain ->
            var original = chain.request()
            if (mPrefs.isUserLoggedIn()) {
                original = original.newBuilder()
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + mPrefs.getAuthenticationToken())
                    .method(original.method(), original.body())
                    .build()
            }
            chain.proceed(original)
        }
    }

}
