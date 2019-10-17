package com.codebase.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.codebase.mvpBase.MvpView
import com.codebase.utils.AppUtils
import com.codebase.utils.DialogsUtil
import com.codebase.utils.PreferenceManager
import com.codebase.utils.ProgressBarHandler
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class  BaseActivity : AppCompatActivity() , MvpView {

    @Inject
    lateinit var mProgressHandler: ProgressBarHandler

    @Inject
    lateinit var  mAppUtils: AppUtils

    @Inject
    lateinit var  mPrefs: PreferenceManager

    @Inject
    lateinit var mDialogsUtil: DialogsUtil

    lateinit var mNameViews: MutableList<View>

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        (application as JoinTrainApplication).getAppComponent().inject(this)
        /* Bind all activity layout here*/
        mPrefs = PreferenceManager(this)
        mProgressHandler.setContext(this)
    }

    /*show ProgressBar for long running operations and
      disable views so that user cannot perform any task*/
    override fun showProgress() {
        mProgressHandler.showProgress()
    }

    /*hide Progressbar and enable views for user interaction*/
    override fun hideProgress() {
        mProgressHandler.hideProgress()
    }

    /* handle any error during any operation and display proper message to user*/
    override fun onError(t: Throwable) {
        t.printStackTrace()
        //  Log.d("onError",t.getMessage());
        mAppUtils.showErrorMessage(window.decorView.rootView, t)
    }


    /* handle any logical error here and display message to user (Maybe in case of invalid credentials)
    @param message warning message to be displayed to user
     */
    override  fun onException(message: String) {
        mAppUtils.showToast(message)
    }

    override  fun onAuthenticationFailed(message: String) {
        mAppUtils.showToast(message)
        mPrefs.setUserLoggedIn(false)
    }

    fun <T : Activity> KClass<T>.start(activity: Activity, finish: Boolean = false) {
        Intent(activity, this.java).apply {
            activity.startActivity(this)
        }
        if (finish) {
            activity.finish()
        }
    }

    fun <T : Activity> KClass<T>.start(activity: Activity, finish: Boolean = false,bundle:Bundle) {
        Intent(activity, this.java).putExtras(bundle).apply {
            activity.startActivity(this)
        }
        if (finish) {
            activity.finish()
        }
    }


    fun <iv : ImageView> KClass<iv>.setNavigationHeader() {
        if(mPrefs.isUserLoggedIn()){

        }else{

        }
    }

}