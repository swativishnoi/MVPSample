package com.codebase.modules.userAccountModule

import com.codebase.mvpBase.MvpView

interface AccountView : MvpView{
    fun onSuccess(response: Any)

    fun enableButton()

    fun disableButton()

}