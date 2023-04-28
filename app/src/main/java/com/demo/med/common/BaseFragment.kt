package com.demo.med.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

abstract class BaseFragment : Fragment() {

    abstract fun layoutId(): Int
    val sharedPrefsHelpers = SpUtil.instance

    val userName = sharedPrefsHelpers?.getString(USERNAME)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(layoutId(), container, false)

    fun getTodayDate(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    fun getTime(): String {
        val currentTime = Calendar.getInstance().time
        val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
        return timeFormat.format(currentTime)
    }

    override fun onStart() {
        super.onStart()
    }
}