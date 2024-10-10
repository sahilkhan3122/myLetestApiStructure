package com.app.myapistructure.network.domain

import com.app.myapistructure.network.domain.ApiObject.ApiEndPoint.LOGIN
import com.app.myapistructure.ui.login.LoginResponse
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST(LOGIN)
    suspend fun login(@FieldMap hashMap: HashMap<String, String>): Response<LoginResponse>


}