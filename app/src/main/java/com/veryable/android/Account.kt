package com.veryable.android

import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("id")
    var id: Int,
    @SerializedName("account_type")
    var type: String,
    @SerializedName("account_name")
    var name: String,
    @SerializedName("desc")
    var desc: String
)
