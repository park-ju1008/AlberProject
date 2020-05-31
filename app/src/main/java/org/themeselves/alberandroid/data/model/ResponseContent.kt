package org.themeselves.alberandroid.data.model

data class ResponseContent<T>(
    val code: String,
    val data: T,
    val message: String,
    val remark: FieldError,
    val status: Int,
    val timestamp: String
)