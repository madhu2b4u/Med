package com.demo.med.common.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.demo.med.R

fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun String.toNumericString() = this.filter { it.isDigit() }

fun Activity.openActivityRight() {
    overridePendingTransition(R.anim.enter_slide_right, R.anim.exit_slide_left)
}

fun Activity.exitActivityBottom() {
    finish()
    overridePendingTransition(R.anim.enter_slide_bottom, R.anim.exit_slide_bottom)
}

fun Activity.exitActivityLeft() {
    finish()
    overridePendingTransition(R.anim.enter_slide_left, R.anim.exit_slide_right)
}

fun noCrash(enableLog: Boolean = true, func: () -> Unit): String? {
    return try {
        func()
        null
    } catch (e: Exception) {
        if (enableLog)
            e.printStackTrace()
        e.message
    }
}

fun TextView.simpletext(value: String) {
    this.text = value
}


fun ViewGroup.inflate(layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

class ContextHandler
    (private val context: Context) {
    val appContext: Context get() = context.applicationContext
}

inline val buildIsMAndLower: Boolean
    get() = Build.VERSION.SDK_INT <= Build.VERSION_CODES.M


