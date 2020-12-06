package com.wojdor.polygonwatchface.configuration.choose

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.wojdor.commonandroid.extension.getIntList
import com.wojdor.polygonwatchface.R
import kotlinx.android.synthetic.main.activity_choose_color.*

class ChooseColorActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_color)
        with(chooseColorItems) {
            isEdgeItemsCenteringEnabled = true
            setHasFixedSize(true)
            adapter = ChooseColorAdapter(getIntList(R.array.configuration_time_colors)) {
                onColorChosen(it)
            }
            requestFocus()
        }
    }

    private fun onColorChosen(color: Int) {
        setResult(RESULT_OK, Intent().apply { putExtra(COLOR_KEY, color) })
        finish()
    }

    companion object {
        const val COLOR_KEY = "COLOR_KEY"
    }
}