package com.anb.viewer

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
            text = "ANB Viewer & Access"
            textSize = 28f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        })

        layout.addView(TextView(this).apply {
            text = "Connect to an authorized support session"
            textSize = 16f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
            setPadding(0, 16, 0, 24)
        })

        layout.addView(Button(this).apply {
            text = "Pair device — coming soon"
            isEnabled = false
        })

        layout.addView(TextView(this).apply {
            text = "No device is connected. Pairing and session " +
                "authorization will be implemented next."
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
            setPadding(0, 24, 0, 0)
        })

        setContentView(layout)
    }
}
