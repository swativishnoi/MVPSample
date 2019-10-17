package com.codebase.modules.userAccountModule.signUpScreen

import android.os.Bundle
import android.util.Log
import com.codebase.R
import com.codebase.base.BaseActivity
import com.codebase.base.JoinTrainApplication
import com.codebase.modules.userAccountModule.AccountView
import javax.inject.Inject

class SignUpActivity : BaseActivity(), AccountView {

    @Inject lateinit var mPresenter: SignUpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as JoinTrainApplication).getAppComponent().inject(this)
        setContentView(R.layout.activity_sign_up)
        mProgressHandler.setContext(this)
        mPresenter.injectDependencies(this)
        mPresenter.attachView(this)

        Log.d("getDeviceToken",mPrefs.getDeviceToken())

        doSignup("vswati.vishnoi@gmail.com","12345678")
    }

    override fun onSuccess(response: Any) {
         //To change body of created functions use File | Settings | File Templates.
    }

    override fun enableButton() {
       //To change body of created functions use File | Settings | File Templates.
    }

    override fun disableButton() {

    }

    private fun doSignup(name:String, email:String){
        try {
            if (mAppUtils.isInternetConnected()) {
                mAppUtils.hideSoftKeyboard(window.decorView.rootView)
                disableButton()
                mPresenter.signupUser(email, email)
            } else {
                enableButton()
                mAppUtils.showSnackBar(window.decorView.rootView, resources.getString(R.string.toast_network_not_available))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
