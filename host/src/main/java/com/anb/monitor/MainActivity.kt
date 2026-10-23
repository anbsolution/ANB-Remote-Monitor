package com.anb.monitor

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.CheckBox
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

    private fun addGap(box: LinearLayout, height: Int) {
        box.addView(View(this), LinearLayout.LayoutParams(1, dp(height)))
    }

    private fun addPanel(root: LinearLayout): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(18), dp(18), dp(18), dp(18))
            setBackgroundColor(panel)
            root.addView(this, LinearLayout.LayoutParams(-1, -2).apply {
                bottomMargin = dp(16)
            })
        }
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
        addGap(root, 8)
        root.addView(label("Monitor Host", 30f, Color.WHITE, true))
        addGap(root, 8)
        root.addView(label("Secure, consent-based device support", 15f, muted))
        addGap(root, 22)

        val status = addPanel(root)
        status.addView(label("SESSION STATUS", 12f, muted, true))
        addGap(status, 10)
        status.addView(label("●  Not connected", 20f, Color.rgb(255, 194, 90), true))
        addGap(status, 8)
        status.addView(label("No screen or camera stream is active.", 14f, muted))

        val consent = addPanel(root)
        consent.addView(label("Sharing permissions", 21f, Color.WHITE, true))
        addGap(consent, 6)
        consent.addView(label(
            "Choose what you may want to share. These selections do not start sharing.",
            14f, muted
        ))
        addGap(consent, 12)

        val screenConsent = CheckBox(this).apply {
            text = "I want to share my screen"
            setTextColor(Color.WHITE)
            buttonTintList = android.content.res.ColorStateList.valueOf(accent)
        }
        val cameraConsent = CheckBox(this).apply {
            text = "I want to share my camera"
            setTextColor(Color.WHITE)
            buttonTintList = android.content.res.ColorStateList.valueOf(accent)
        }
        consent.addView(screenConsent)
        consent.addView(cameraConsent)
        addGap(consent, 12)

        consent.addView(Button(this).apply {
            text = "Review consent"
            setBackgroundColor(accent)
            setTextColor(Color.WHITE)
            setOnClickListener {
                if (!screenConsent.isChecked && !cameraConsent.isChecked) {
                    Toast.makeText(
                        this@MainActivity,
                        "Select a sharing option first, or leave sharing disabled.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val choices = buildList {
                        if (screenConsent.isChecked) add("Screen sharing")
                        if (cameraConsent.isChecked) add("Camera sharing")
                    }.joinToString("\n")
                    AlertDialog.Builder(this@MainActivity)
                        .setTitle("Review your choice")
                        .setMessage(
                            "$choices\n\nThis build does not yet connect to a viewer " +
                            "or start capture. No sharing will begin."
                        )
                        .setNegativeButton("Cancel", null)
                        .setPositiveButton("Understood", null)
                        .show()
                }
            }
        })

        val security = addPanel(root)
        security.addView(label("Privacy & safety", 20f, Color.WHITE, true))
        addGap(security, 8)
        security.addView(label(
            "• No hidden monitoring\n" +
            "• No sharing starts from this screen\n" +
            "• Pairing and authentication are not connected yet\n" +
            "• Stop/cancel controls will be required for live sessions",
            14f, muted
        ))

        setContentView(scroll)
    }
}
