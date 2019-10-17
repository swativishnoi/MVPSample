package com.codebase.dagger

import android.content.Context
import com.codebase.constants.ValueConstants
import com.codebase.utils.AppUtils
import com.codebase.utils.DialogsUtil
import com.codebase.utils.PreferenceManager
import com.codebase.utils.ProgressBarHandler
import dagger.Module
import dagger.Provides
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

/**
 * Provide objects for utility objects
 * to be included in component where util class objects are required
 */

@Module
class UtilsModule(val mContext: Context) {

    @Provides
    @Singleton
    fun getAppUtils(): AppUtils {
        return AppUtils(mContext)
    }

    // get ProgressBarHandler instance
    @Provides
    @Singleton
    fun getProgressBar(): ProgressBarHandler {
        return ProgressBarHandler()
    }

    //get dialog utils
    @Provides
    @Singleton
    fun getDialogUtils(): DialogsUtil {
        return DialogsUtil()
    }


    //get new thread
    @Provides
    @Singleton
    @Named(ValueConstants.NEW_THREAD)
    fun getNewThread(): Scheduler {
        return Schedulers.io()
    }

    //get main thread
    @Provides
    @Singleton
    @Named(ValueConstants.MAIN_THREAD)
    fun getMainThread():Scheduler
    {
        return AndroidSchedulers.mainThread()
    }

    //get Preference Manager
    @Provides
    @Singleton
    fun getPreferences(): PreferenceManager {
        return PreferenceManager(mContext)
    }
}
