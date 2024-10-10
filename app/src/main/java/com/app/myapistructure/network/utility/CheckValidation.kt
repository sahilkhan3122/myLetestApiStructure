package com.app.myapistructure.network.utility

import android.content.Context
import com.example.myapistructure.R
import javax.inject.Inject


open class CheckValidation @Inject constructor(val context: Context) {

    fun loginValidation(mobileNumber: String, password: String): Result<Any, Any> {
        return if (mobileNumber.isEmpty()) {
            Result.Error(
                LoginError.ENTER_NUMBER,
                context.getString(R.string.please_enter_number)
            )
        } else if (password.isEmpty()) {
            Result.Error(
                LoginError.ENTER_PASSWORD,
                context.getString(R.string.please_enter_password)
            )
        } else {
            Result.Success(true, "")
        }
    }

    fun networkError(code: Int): String {
        return when (code) {
            400 -> context.getString(R.string.bad_request)
            401 -> context.getString(R.string.unauthorized)
            403 -> context.getString(R.string.forbidden)
            404 -> context.getString(R.string.not_found)
            405 -> context.getString(R.string.method_not_allowed)
            409 -> context.getString(R.string.conflict)
            422 -> context.getString(R.string.unprocessable_entity)
            500 -> context.getString(R.string.internal_server_error)
            502 -> context.getString(R.string.bad_gateway)
            503 -> context.getString(R.string.service_unavailable)
            504 -> context.getString(R.string.gateway_timeout)
            else -> context.getString(R.string.unknown)
        }
    }

}