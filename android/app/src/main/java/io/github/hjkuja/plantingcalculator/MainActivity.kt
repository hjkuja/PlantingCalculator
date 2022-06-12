package io.github.hjkuja.plantingcalculator

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText

const val EXTRA_MESSAGE = "io.github.hjkuja.MESSAGE1"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calculate(view: View) {
        val grainValue = findViewById<EditText>(R.id.grainWeightInput)
        val message = grainValue.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }

}