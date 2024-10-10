package com.app.myapistructure.network.domain

class ApiObject {

    object ApiHeaderKey {

        const val X_API_KEY = "x-api-key"
    }

    object ApiHeaderValue {
        const val X_API_KEY_VALUE = "my-rickshaw"
    }

    object ApiEndPoint {
        const val LOGIN = "login"


    }

    object Param {

        //Login
        const val MOBILE_NUMBER = "mobile_number"
        const val PASSWORD = "password"
        const val DEVICE_TOKEN = "device_token"
        const val DEVICE_TYPE = "device_type"

    }

}