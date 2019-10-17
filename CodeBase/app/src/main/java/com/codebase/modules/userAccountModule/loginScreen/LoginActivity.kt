package com.codebase.modules.userAccountModule.loginScreen

import android.os.Bundle
import com.codebase.R
import com.codebase.base.BaseActivity
import com.codebase.base.JoinTrainApplication
import com.codebase.modules.userAccountModule.AccountView
import javax.inject.Inject

class LoginActivity : BaseActivity(), AccountView {

    @Inject lateinit var mPresenter: LoginPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as JoinTrainApplication).getAppComponent().inject(this)
        setContentView(R.layout.activity_login)
        mProgressHandler.setContext(this@LoginActivity)
        mPresenter.injectDependencies(this)
        mPresenter.attachView(this)
    }

    override fun onSuccess(response: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun enableButton() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun disableButton() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun doLogin(password:String, email:String){
        try {
            if (mAppUtils.isInternetConnected()) {
                mAppUtils.hideSoftKeyboard(window.decorView.rootView)
                disableButton()
                //mPresenter.loginUser(email, password)
            } else {
                enableButton()
                mAppUtils.showSnackBar(window.decorView.rootView, resources.getString(R.string.toast_network_not_available))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}
