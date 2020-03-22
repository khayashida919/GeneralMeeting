package com.example.generalmeeting.utility

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun Activity.hideKeyboard() {
    val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val a = currentFocus?.windowToken
    manager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}