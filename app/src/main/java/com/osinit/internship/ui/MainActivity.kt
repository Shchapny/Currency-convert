package com.osinit.internship.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.osinit.internship.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_CurrencyConvert)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}