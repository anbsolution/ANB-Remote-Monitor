package com.anb.viewer

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.text.InputFilter
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    private val bg = Color.rgb(10, 15, 30)
    private val panel = Color.rgb(23, 32, 53)
    private val accent = Color.rgb(91, 130, 255)
    private val muted = Color.rgb(174, 187, 211)

    private fun dp(v: Int) = (v * resources.displayMetrics.density).toInt()

    private fun label(text: String, size: Float, color: Int, bold: Boolean = false): TextView {
        return TextView(this).apply {
            this.text = text
            textSize = size
            setTextColor(color)
            if (bold) setTypeface(null, Typeface.BOLD)
        }
    }

    private fun gap(box: LinearLayout, height: Int) {
        box.addView(View(this), LinearLayout.LayoutParams(1, dp(height)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(20), dp(24), dp(20), dp(24))
            setBackgroundColor(bg)
        }
        val scroll = ScrollView(this).apply { addView(root) }

        root.addView(label("ANB", 14f, accent, true))
        gap(root, 8)
        root.addView(label("Viewer & Access", 30f, Color.WHITE, true))
        gap(root, 8)
        root.addView(label("Connect only to a device you are authorized to access.", 15f, muted))
        gap(root, 22)

        val status = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(18), dp(18), dp(18), dp(18))
            setBackgroundColor(panel)
        }
        root.addView(status, LinearLayout.LayoutParams(-1, -2).apply {
            bottomMargin = dp(16)
        })
        status.addView(label("CONNECTION STATUS", 12f, muted, true))
        gap(status, 10)
        status.addView(label("●  Not connected", 20f, Color.rgb(255, 194, 90), true))
        gap(status, 8)
        status.addView(label("No host device is paired.", 14f, muted))

        val pair = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(18), dp(18), dp(18), dp(18))
            setBackgroundColor(panel)
        }
        root.addView(pair, LinearLayout.LayoutParams(-1, -2).apply {
            bottomMargin = dp(16)
        })
        pair.addView(label("Pair a device", 21f, Color.WHITE, true))
        gap(pair, 8)
        pair.addView(label(
            "Enter the 6-digit code shown by the host device. Pairing service is not connected in this build.",
            14f, muted
        ))
        gap(pair, 16)

        val code = EditText(this).apply {
            hint = "6-digit pairing code"
            setHintTextColor(muted)
            setTextColor(Color.WHITE)
            textSize = 20f
            gravity = android.view.Gravity.CENTER
            inputType = InputType.TYPE_CLASS_NUMBER
            filters = arrayOf(InputFilter.LengthFilter(6))
            setPadding(dp(12), dp(14), dp(12), dp(14))
            setBackgroundColor(Color.rgb(34, 45, 69))
        }
        pair.addView(code, LinearLayout.LayoutParams(-1, -2))
        gap(pair, 14)

        pair.addView(Button(this).apply {
            text = "Continue to pairing"
            setBackgroundColor(accent)
            setTextColor(Color.WHITE)
            setOnClickListener {
                val value = code.text.toString()
                if (!value.matches(Regex("\\d{6}"))) {
                    code.error = "Enter exactly 6 digits"
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Pairing backend is not connected yet. No connection was made.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })

        val privacy = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(18), dp(18), dp(18), dp(18))
            setBackgroundColor(panel)
        }
        root.addView(privacy)
        privacy.addView(label("Before connecting", 20f, Color.WHITE, true))
        gap(privacy, 8)
        privacy.addView(label(
            "• Host approval must be required\n" +
            "• Screen and camera access must be separately approved\n" +
            "• A live session must show a visible indicator\n" +
            "• The host must be able to end the session",
            14f, muted
        ))

        setContentView(scroll)
    }
}
