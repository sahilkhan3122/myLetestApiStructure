package com.app.myapistructure.base

import com.google.gson.annotations.SerializedName

open class BaseResponse {

    @SerializedName("status")
    var status: Boolean = false

    @SerializedName("message")
    var message: String = ""

}