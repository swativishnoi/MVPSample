package com.codebase.modules.userAccountModule.signUpScreen

import android.content.Context
import android.os.Build
import com.codebase.api.RestService
import com.codebase.base.JoinTrainApplication
import com.codebase.constants.ApiConstants
import com.codebase.constants.ValueConstants
import com.codebase.modules.userAccountModule.AccountView
import com.codebase.mvpBase.BasePresenter
import com.codebase.utils.PreferenceManager
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject



class SignUpPresenter @Inject constructor() : BasePresenter<AccountView>() {

    @Inject
    lateinit var mRestService: RestService

    @Inject
    lateinit var mPrefs: PreferenceManager

    lateinit var context: Context
    fun injectDependencies(context: Context) {
        (context.applicationContext as JoinTrainApplication).getAppComponent().inject(this)
        this.context=context
    }

   fun signupUser(name:String, email:String){
        getMvpView().showProgress()
       val pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0)
       val version = pInfo.versionName
        mRestService.login(name,email,ValueConstants.DEVICE_TYPE,version)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { ResponseModel ->
                    if (this.isViewAttached()) {
                        if (ResponseModel.status == ApiConstants.STATUS_SUCCESS_200) {
                            this@SignUpPresenter.getMvpView().enableButton()
                            this@SignUpPresenter.getMvpView().onSuccess(ResponseModel)
                        } else {
                            this@SignUpPresenter.getMvpView().enableButton()
                            try {
                                this@SignUpPresenter.getMvpView().onException(ResponseModel.message)
                            } catch (e:Exception) {
                                e.printStackTrace()
                            }finally {

                            }
                        }

                        this@SignUpPresenter.getMvpView().hideProgress()
                    }
                },
                { throwable ->
                    if (isViewAttached()) {
                        getMvpView().enableButton()
                        getMvpView().hideProgress()
                        getMvpView().onError(throwable)
                    }
                }
            )
    }


}