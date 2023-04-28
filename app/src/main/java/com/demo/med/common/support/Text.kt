package com.demo.med.common.support

import android.content.Context
import androidx.annotation.StringRes
import java.io.Serializable

sealed class Text : Serializable {

    data class AsResource(@StringRes val res: Int) : Serializable, Text()
    data class AsString(val title: String) : Serializable, Text()

    fun getValue(context: Context): String = when (this) {
        is AsResource -> context.resources.getString(res)
        is AsString -> title
    }

    companion object {
        fun toText(message: Message): Text {
            return when (message) {
                is Message.AsResource -> AsResource(message.res)
                is Message.AsString -> AsString(message.message)
            }
        }
    }
}

sealed class Message : Serializable {
    data class AsResource(@StringRes val res: Int) : Serializable, Message()
    data class AsString(val message: String) : Serializable, Message()
}
