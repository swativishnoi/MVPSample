package com.codebase.modules.mainModule.homeScreen

import com.codebase.mvpBase.MvpView

interface HomeView : MvpView{
    fun onSuccess(response: Any)

    fun enableButton()

    fun disableButton()

}