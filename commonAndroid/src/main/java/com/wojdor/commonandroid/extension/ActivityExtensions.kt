package com.wojdor.commonandroid.extension

import android.app.Activity
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun Activity.showSnackbar(@StringRes textResId: Int) {
    Snackbar.make(findViewById(android.R.id.content), textResId, Snackbar.LENGTH_SHORT).show()
}
