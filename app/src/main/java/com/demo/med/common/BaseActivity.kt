package com.demo.med.common

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    val sharedPrefsHelpers = SpUtil.instance

}