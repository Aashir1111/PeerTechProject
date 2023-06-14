package com.app.testapp.model

data class Error @JvmOverloads constructor(
    var code: Int? = 0,
    val message: String = ""
)