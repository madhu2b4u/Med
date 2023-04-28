package com.demo.med.common.support

import android.widget.TextView
import java.util.Locale

fun TextView.showWithTextOrHide(
    text: String?
) {
    when {
        text.isNullOrEmpty() -> hide()
        else -> {
            setText(text)
            show()
        }
    }
}


fun String.isNotEmptyAndNotBlank(): Boolean {
    return isNotEmpty() && isNotBlank()
}

fun String.toLowerCaseWithDefaultLocale(): String {
    return lowercase(Locale.getDefault())
}
