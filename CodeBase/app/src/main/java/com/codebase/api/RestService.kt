package com.codebase.api

import com.codebase.base.ResponseModel
import com.codebase.constants.ApiConstants
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

interface RestService {

    //LOGIN API
    @FormUrlEncoded
    @POST(ApiConstants.LOGIN)
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("os_type") device_type: String,
        @Field("app_version_no") version_name: String
    ): Observable<ResponseModel>


    //SIGNUP API
    @FormUrlEncoded
    @POST(ApiConstants.SIGNUP)
    fun signUp(
        @Field("email") email: String,
        @Field("password") password: String
    ): Observable<ResponseModel>


}