package com.codebase.mvpBase

interface MvpView {
    fun showProgress()

    fun hideProgress()

    fun onError(t: Throwable)

    fun onException(message: String)

    fun onAuthenticationFailed(message: String)
}