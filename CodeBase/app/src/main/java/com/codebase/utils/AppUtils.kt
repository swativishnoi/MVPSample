package com.codebase.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.support.design.widget.Snackbar
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.codebase.R
import com.muddzdev.styleabletoastlibrary.StyleableToast
import retrofit2.adapter.rxjava.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

class AppUtils(mContext:Context) {
    val mContext : Context

    init {
        this.mContext=mContext
    }

    /* Description : Check if user is online or not*/
    fun isInternetConnected(): Boolean {
        val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }


   /*Description : Hide the soft keyboard*/
    fun hideSoftKeyboard(view: View) {
        val inputMethodManager = mContext.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun hideSoftKeyboardOut(activity: Activity) {
        val inputMethodManager = activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            activity.currentFocus!!.windowToken, 0
        )
    }

   /*Show snackbar*/
    fun showSnackBar(view: View, text: String) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
    }


    /*Show showToast*/
    fun showToast(text: String) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show()
    }

    /*Show showSuccessToast with custom colors */
    fun showSuccessToast(text: String) {
        StyleableToast.Builder(mContext)
            .text(text)
            .textColor(Color.WHITE)
            .backgroundColor(mContext.resources.getColor(R.color.colorAccent))
            .show()
    }

    /*show related error message to user on api failure */
    fun showErrorMessage(view: View, t: Throwable) {
        StyleableToast.Builder(mContext)
            .text(getErrorMessage(t))
            .textColor(Color.WHITE)
            .backgroundColor(mContext.resources.getColor(R.color.colorAccent))
            .show()
    }

    /*return error message from webservice error code*/
    private fun getErrorMessage(throwable: Throwable): String {
        val errorMessage: String
        if (throwable is HttpException) {
            when (throwable.code()) {
                400 -> errorMessage = mContext.resources.getString(R.string.error_400)
                500 -> errorMessage = mContext.resources.getString(R.string.error_500)
                401 -> errorMessage = mContext.resources.getString(R.string.error_401)
                else -> errorMessage = mContext.resources.getString(R.string.toast_unfortunately_error)
            }
        } else if (throwable is UnknownHostException || throwable is ConnectException) {
            errorMessage = mContext.resources.getString(R.string.toast_network_not_available)
        } else {
            errorMessage = mContext.resources.getString(R.string.toast_unfortunately_error)
        }
        return errorMessage
    }

}