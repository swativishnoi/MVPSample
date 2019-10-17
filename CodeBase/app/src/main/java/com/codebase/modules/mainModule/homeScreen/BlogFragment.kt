
package com.codebase.modules.mainModule.homeScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codebase.R
import com.codebase.base.BaseFragment
import com.codebase.base.JoinTrainApplication
import javax.inject.Inject

class BlogFragment : BaseFragment() , HomeView{

    @Inject
    lateinit var mPresenter: HomePresenter

    override val layoutId: Int
        get() = R.layout.fragment_blog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_blog, container, false)
        (activity!!.getApplicationContext() as JoinTrainApplication).getAppComponent().inject(this)

        mPresenter.injectDependencies(activity!!)
        mPresenter.attachView(this)
        Log.d("getDeviceToken",mPrefs.getDeviceToken())
        doSignup("rizwan.kayani@elasticbrainz.com","12345678")
        return view
    }


    override fun onAuthenticationFailed(message: String) {

    }

    override fun onSuccess(response: Any) {
        Log.d("onSuccess","Fragment")
    }

    override fun enableButton() {
        Log.d("enableButton","Fragment")
    }

    override fun disableButton() {
        Log.d("disableButton","Fragment")
    }


    private fun doSignup(name:String, email:String){
        try {
            if (mAppUtils.isInternetConnected()) {
                disableButton()
                mPresenter.signupUser(email, email)
            } else {
                enableButton()
                mAppUtils.showSnackBar(activity!!.window.decorView.rootView, resources.getString(R.string.toast_network_not_available))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
