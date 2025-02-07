package com.app.myapistructure.ui.login

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.app.myapistructure.network.domain.ApiObject.Param.DEVICE_TOKEN
import com.app.myapistructure.network.domain.ApiObject.Param.DEVICE_TYPE
import com.app.myapistructure.network.domain.ApiObject.Param.MOBILE_NUMBER
import com.app.myapistructure.network.domain.ApiObject.Param.PASSWORD
import com.app.myapistructure.network.utility.ResponseData
import com.app.myrickshawparent.base.BaseViewModel
import com.app.myapistructure.network.utility.Result
import com.app.myapistructure.util.failMsg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val context: Context) : BaseViewModel() {

    @Inject
    lateinit var loginRepository: LoginRepository

    var number = ObservableField("")
    var passsword = ObservableField("")

    private val loginMutableStateFlow: MutableStateFlow<ResponseData<Any>> =
        MutableStateFlow(ResponseData.Empty())
    var loginStateFlow: StateFlow<ResponseData<Any>> = loginMutableStateFlow

    fun login() {
        when (val checkValidation =
            checkValidation.loginValidation(
                number.get().toString().trim(),
                passsword.get().toString().trim()
            )) {
            is Result.Error -> {
                loginMutableStateFlow.value = ResponseData.Error(
                    data = checkValidation.data,
                    error = checkValidation.error
                )
            }

            is Result.Success -> {
                loginMutableStateFlow.value = ResponseData.Success()
                callLoginApi()
            }
        }
    }

    fun callLoginApi() {
        viewModelScope.launch {
            val hashMap = HashMap<String, String>()
            hashMap[MOBILE_NUMBER] = number.get().toString().trim()
            hashMap[PASSWORD] = passsword.get().toString().trim()
            hashMap[DEVICE_TOKEN] = "abc"
            hashMap[DEVICE_TYPE] = "android"
            loginRepository.getLoginData(hashMap).onStart {
                loginMutableStateFlow.value = ResponseData.Loading()
            }.catch {
                loginMutableStateFlow.value =
                    ResponseData.Error(error = context.failMsg(it.message.toString()))
            }.collect {
                loginMutableStateFlow.value = it
            }
        }
    }
}