package com.codebase.modules.userAccountModule.loginScreen

import android.content.Context
import com.codebase.api.RestService
import com.codebase.base.JoinTrainApplication
import com.codebase.modules.userAccountModule.AccountView
import com.codebase.mvpBase.BasePresenter
import com.codebase.utils.PreferenceManager
import javax.inject.Inject

class LoginPresenter @Inject constructor(): BasePresenter<AccountView>() {
    //Injecting dependencies required  by our presenter


    @Inject
    lateinit var mRestService: RestService

    @Inject
    lateinit var mPrefs: PreferenceManager




    fun injectDependencies(context: Context) {
        (context.applicationContext as JoinTrainApplication).getAppComponent().inject(this)
    }



}