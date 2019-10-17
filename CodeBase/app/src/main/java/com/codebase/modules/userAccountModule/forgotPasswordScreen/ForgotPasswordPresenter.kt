package com.codebase.modules.userAccountModule.forgotPasswordScreen

import android.content.Context
import com.codebase.api.RestService
import com.codebase.base.JoinTrainApplication
import com.codebase.modules.userAccountModule.AccountView
import com.codebase.mvpBase.BasePresenter
import com.codebase.utils.PreferenceManager
import javax.inject.Inject

class ForgotPasswordPresenter @Inject constructor(): BasePresenter<AccountView>() {
    //Injecting dependencies required  by our presenter

  /*  @Inject
    @Named(ValueConstants.MAIN_THREAD)
    lateinit var mMainThread: Scheduler

    @Inject
    @Named(ValueConstants.NEW_THREAD)
    lateinit var mNewThread: Scheduler*/

    @Inject
    lateinit var mRestService: RestService

    @Inject
    lateinit var mPrefs: PreferenceManager

    fun injectDependencies(context: Context) {
        (context.applicationContext as JoinTrainApplication).getAppComponent().inject(this)
    }



}