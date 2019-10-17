package com.codebase.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codebase.mvpBase.MvpView
import com.codebase.utils.AppUtils
import com.codebase.utils.DialogsUtil
import com.codebase.utils.PreferenceManager
import com.codebase.utils.ProgressBarHandler
import javax.inject.Inject

abstract class BaseFragment : Fragment(), MvpView {

    @Inject
    lateinit var mProgressHandler1: ProgressBarHandler

    @Inject
    lateinit var mAppUtils: AppUtils

    @Inject
    lateinit var mPrefs: PreferenceManager

    @Inject
    lateinit var mDialogUtil: DialogsUtil

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId, container, false)
        (activity!!.application as JoinTrainApplication).getAppComponent().inject(this)
        mPrefs = PreferenceManager(activity!!)
        return view
    }

    /*handle any error during any operation and display proper message to user*/
    override fun onError(t: Throwable) {
        t.printStackTrace()
        mAppUtils.showErrorMessage(activity!!.window.decorView.rootView, t)
    }

    override  fun onException(message: String) {
        mAppUtils.showSnackBar(activity!!.window.decorView.rootView, message)
    }
    /*show ProgressBar for long running operations and
       disable views so that user cannot perform any task*/
    override fun showProgress() {
        mProgressHandler1.showProgress()
    }

    /*hide Progressbar and enable views for user interaction*/
    override fun hideProgress() {
        mProgressHandler1.hideProgress()
    }
}
