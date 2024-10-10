package com.app.myapistructure.ui.login

import android.content.Context
import com.app.myapistructure.network.domain.ApiInterface
import com.app.myapistructure.network.domain.getResponseResult
import com.app.myapistructure.network.utility.ResponseData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val context: Context,
) {

    suspend fun getLoginData(hashMap: HashMap<String, String>): Flow<ResponseData<LoginResponse>> {
        return flow {
            val loginResponse = apiInterface.login(hashMap)
            emit(getResponseResult(loginResponse, context))
        }.flowOn(Dispatchers.IO)
    }
}