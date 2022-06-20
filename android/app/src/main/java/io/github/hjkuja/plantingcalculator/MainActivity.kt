package io.github.hjkuja.plantingcalculator

import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import io.github.hjkuja.plantingcalculator.R.id.*
import java.lang.NumberFormatException
import kotlin.math.roundToInt

const val EXTRA_MESSAGE = "io.github.hjkuja.CALCULATION_RESULT"

var grainKey = "gw"
var densityKey = "de"
var germinKey = "ge"

var grainWeight: Double? = null
var targetDensity: Double? = null
var germinability: Double? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_PlantingCalculator)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun calculateResult(view: View) {
        // Get values from inputs and conver to them to Double
        grainWeight = getEditTextInputValueAsDouble(num_grain_weight)
        targetDensity = getEditTextInputValueAsDouble(num_target_density)
        germinability = getEditTextInputValueAsDouble(num_germinability)

        if (grainWeight == null || targetDensity == null || germinability == null){
            // TODO: Could show error message to user
            return
        }

        // Calculate the value
        val result = (grainWeight!! * targetDensity!!) / germinability!!

        // Round the result and send it to the next activity
        val message = ((result * 100.0).roundToInt() / 100.0).toString()
        findViewById<TextView>(lbl_calculation).text = message
    }

    private fun getEditTextInputValueAsDouble(inputId: Int): Double? {
        // If view is not found, return null
        val view: EditText? = findViewById<EditText>(inputId) ?: return null

        // If view is found, try to convert the value to a Double and return it
        val viewText = view?.text.toString()
        return try {
            viewText.toDouble()
        } catch (exception: Exception){
            // Handle error
            var i = 0
            if (exception::class == NumberFormatException::class){
                if (viewText != ""){
                    view?.setTextColor(Color.RED)
                }
                view?.setHintTextColor(Color.RED)
            }
            null
        }
    }
}
