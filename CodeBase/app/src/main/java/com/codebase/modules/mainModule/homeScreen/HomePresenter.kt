package com.codebase.modules.mainModule.homeScreen

import android.content.Context
import com.codebase.api.RestService
import com.codebase.base.JoinTrainApplication
import com.codebase.constants.ApiConstants
import com.codebase.constants.ValueConstants
import com.codebase.mvpBase.BasePresenter
import com.codebase.utils.PreferenceManager
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class HomePresenter @Inject constructor() : BasePresenter<HomeView>() {

    @Inject
    lateinit var mRestService: RestService

    @Inject
    lateinit var mPrefs: PreferenceManager
    lateinit var context: Context
    fun injectDependencies(context: Context) {
        this.context=context
        (context.applicationContext as JoinTrainApplication).getAppComponent().inject(this)
    }

   fun signupUser(name:String, email:String){
        getMvpView().showProgress()
       val pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0)
       val version = pInfo.versionName
        mRestService.login(name,email, ValueConstants.DEVICE_TYPE,version)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { ResponseModel ->
                    if (this.isViewAttached()) {
                        if (ResponseModel.status == ApiConstants.STATUS_SUCCESS_200) {
                            this@HomePresenter.getMvpView().enableButton()
                            this@HomePresenter.getMvpView().onSuccess(ResponseModel)
                        } else {
                            this@HomePresenter.getMvpView().enableButton()
                            try {
                                this@HomePresenter.getMvpView().onException(ResponseModel.message)
                            } catch (e:Exception) {
                                e.printStackTrace()
                            }
                        }

                        this@HomePresenter.getMvpView().hideProgress()
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