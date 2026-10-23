package com.anb.monitor

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
            setBackgroundColor(Color.rgb(12, 18, 35))
        }

        layout.addView(TextView(this).apply {
            text = "ANB Monitor Host"
            textSize = 28f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        })

        layout.addView(TextView(this).apply {
            text = "Authorized remote support"
            textSize = 16f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
            setPadding(0, 16, 0, 24)
        })

        layout.addView(Button(this).apply {
            text = "Screen sharing — not started"
            isEnabled = false
        })

        layout.addView(TextView(this).apply {
            text = "Screen and camera sharing are not active. " +
                "Future sessions will require explicit consent."
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
            setPadding(0, 24, 0, 0)
        })

        setContentView(layout)
    }
}
