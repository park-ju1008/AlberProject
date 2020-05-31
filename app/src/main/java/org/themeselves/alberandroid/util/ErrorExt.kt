package org.themeselves.alberandroid.util

import org.themeselves.alberandroid.R

fun String.getSnackbarMessage() =
    when (this) {
        "400" -> R.string.failed_load_data_400
        "401" -> R.string.failed_load_data_401
        "403" -> R.string.failed_load_data_403
        "404" -> R.string.failed_load_data_404
        "500" -> R.string.failed_load_data_500
        "timeout" -> R.string.failed_load_data_timeout
        else -> R.string.failed_load_data_unknown
    }
