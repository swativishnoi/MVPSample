package com.codebase.mvpBase

interface  Presenter<V : MvpView> {

    fun attachView(mvpView: V)

    fun detachView()
}