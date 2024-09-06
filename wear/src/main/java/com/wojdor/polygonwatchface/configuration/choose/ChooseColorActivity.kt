package com.wojdor.polygonwatchface.configuration.choose

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.wojdor.commonandroid.extension.getIntList
import com.wojdor.polygonwatchface.R
import com.wojdor.polygonwatchface.databinding.ActivityChooseColorBinding

class ChooseColorActivity : Activity() {

    private lateinit var binding: ActivityChooseColorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseColorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding.chooseColorItems) {
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
