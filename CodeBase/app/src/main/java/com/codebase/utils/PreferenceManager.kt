package com.codebase.utils

import android.content.Context
import android.content.SharedPreferences
import com.codebase.constants.PreferenceConstants

class PreferenceManager (val mContext: Context){

    //get shared pref
    fun getPreferences(): SharedPreferences {
        return mContext.getSharedPreferences(PreferenceConstants.PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun has(key: String): Boolean {
        return getPreferences().contains(key)
    }

    //save data of current logged in user
    fun setDeviceToken(device_token: String) {
        getPreferences().edit().putString(PreferenceConstants.DEVICE_TOKEN, device_token).apply()
    }

    //get user data as string
    fun getDeviceToken(): String? {
        return getPreferences().getString(PreferenceConstants.DEVICE_TOKEN, "")
    }

    //set true when user is logged in
    fun setUserLoggedIn(isLogin: Boolean) {
        getPreferences().edit().putBoolean(PreferenceConstants.IS_LOGIN, isLogin).apply()
    }

    //returns true when account added
    fun isUserLoggedIn(): Boolean {
        return getPreferences().getBoolean(PreferenceConstants.IS_LOGIN, false)
    }

    //set user data after login
    fun setAuthenticationToken(authenticationToken: String) {
        getPreferences().edit().putString(PreferenceConstants.AUTHENTICATION_TOKEN, authenticationToken).apply()
    }

    //returns user data when required
    fun getAuthenticationToken(): String? {
        return getPreferences().getString(PreferenceConstants.AUTHENTICATION_TOKEN, null)
    }

    //set user data after login
    fun logOut() {
        getPreferences().edit().clear().apply()
    }
}