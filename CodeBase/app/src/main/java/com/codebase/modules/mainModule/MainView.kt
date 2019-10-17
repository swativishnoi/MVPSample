package com.codebase.modules.mainModule

import com.codebase.mvpBase.MvpView

interface MainView : MvpView{
    fun onSuccess(response: Any)

    fun enableButton()

    fun disableButton()

}