package com.project.detectmeal.onboarding

import android.content.Context
import android.content.SharedPreferences

fun onboardingSharedPref(context: Context) {
    val sharedPrefs = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPrefs!!.edit()
    editor.putBoolean("FirstRun", true)
    editor.apply()
}

fun restorePref(context: Context): Boolean {
    val sharedPrefs = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
    val bool = sharedPrefs?.getBoolean("FirstRun", false)
    return bool!!
}