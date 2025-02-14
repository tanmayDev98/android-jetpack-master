package com.jetpack.master.colorSample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jetpack.master.databinding.ActivitySwatchBinding

const val EXTRA_COLOR = "color"

class SwatchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val color = intent.getIntExtra(EXTRA_COLOR, 0x7FFF0000)
        ActivitySwatchBinding.inflate(layoutInflater).apply {
            setContentView(root)
            toolbar.title = "#${Integer.toHexString(color)}"
            swatch.setBackgroundColor(color)
        }
    }
}