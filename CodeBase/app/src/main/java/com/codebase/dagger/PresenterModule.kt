package com.codebase.dagger

import android.content.Context
import com.codebase.mvpBase.BasePresenter
import com.codebase.mvpBase.MvpView
import dagger.Module
import dagger.Provides


@Module
class PresenterModule(val mContext: Context) {
    @Provides
    fun provideActivity(): Context {
        return mContext
    }

    @Provides
    fun providePresenter(): BasePresenter<MvpView> {
        return BasePresenter()
    }


}