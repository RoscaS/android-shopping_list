package com.example.shoppingcart.models

import com.google.gson.annotations.SerializedName

data class Photo(
        @SerializedName("filename")
        var filename: String? = null
)