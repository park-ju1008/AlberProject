package org.themeselves.alberandroid.data.model

data class FieldError(
    val field:String,
    val reason:String,
    val value:String
)