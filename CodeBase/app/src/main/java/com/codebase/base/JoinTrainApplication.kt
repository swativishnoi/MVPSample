package com.codebase.base

import android.os.StrictMode
import android.support.multidex.MultiDexApplication
import com.codebase.api.NetModule
import com.codebase.dagger.PresenterModule
import com.codebase.dagger.UtilsModule

class JoinTrainApplication : MultiDexApplication() {

     lateinit var mAppComponent: AppComponent
     lateinit var instance: JoinTrainApplication

    override fun onCreate() {
        super.onCreate()
        instance=this

        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        //create component
        mAppComponent = initDagger(this)


    }

    fun initDagger(app: JoinTrainApplication):AppComponent{
        lateinit var mAppComponent:AppComponent
        mAppComponent = DaggerAppComponent.builder()
            .utilsModule(UtilsModule(this))
            .netModule(NetModule(this))
            .presenterModule(PresenterModule(this))
            .build()
        return mAppComponent
    }

    fun getAppComponent(): AppComponent {
        return mAppComponent
    }

    fun get(): JoinTrainApplication {
        return instance
    }


    companion object {
        lateinit var instance: JoinTrainApplication private set
    }


}