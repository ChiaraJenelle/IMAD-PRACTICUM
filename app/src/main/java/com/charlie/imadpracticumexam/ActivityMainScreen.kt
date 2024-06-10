package com.charlie.imadpracticumexam

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var buttonNext: Button
    private val dayArray = mutableListOf<String>()
    private val temperatureArrayminTemp = mutableListOf<Float>()
    private val temperatureArraymaxTemp = mutableListOf<Float>()
    private val weatherConArray = mutableListOf<String>()

    private lateinit var day: EditText
    private lateinit var minTemp: EditText
    private lateinit var maxTemp: EditText
    private lateinit var weatherCon: EditText
    private lateinit var tvMessage: TextView
    private lateinit var buttonSave: Button
    private lateinit var buttonClear: Button
    private lateinit var buttonCalc: Button
    private lateinit var averageTemp: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        buttonClear = findViewById(R.id.buttonClear)
        buttonNext = findViewById(R.id.buttonNext)
        day = findViewById(R.id.day)
        minTemp = findViewById(R.id.minTemp)
        maxTemp = findViewById(R.id.maxTemp)
        weatherCon = findViewById(R.id.weatherCon)
        buttonSave = findViewById(R.id.buttonSave)
        tvMessage = findViewById(R.id.tvMessage)
        buttonCalc = findViewById(R.id.buttonCalc)
        averageTemp = findViewById(R.id.averageTemp)

        buttonClear.setOnClickListener {
            day.setText("")
            minTemp.setText("")
            maxTemp.setText("")
            weatherCon.setText("")
        }

        buttonSave.setOnClickListener {
            val screenTimeDate = day.text.toString()
            val screenTimeMorning = minTemp.text.toString()
            val screenTimeAfternoon = maxTemp.text.toString()
            val screenTimeNote = weatherCon.text.toString()

            if (screenTimeDate.isNotEmpty() && screenTimeMorning.isNotEmpty() && screenTimeAfternoon.isNotEmpty()) {
                try {
                    temperatureArrayminTemp.add(screenTimeMorning.toFloat())
                    temperatureArraymaxTemp.add(screenTimeAfternoon.toFloat())
                    weatherConArray.add(screenTimeNote)
                    weatherConArray.add(screenTimeDate)

                    day.text.clear()
                    minTemp.text.clear()
                    maxTemp.text.clear()
                    weatherCon.text.clear()
                    tvMessage.text = ""
                } catch (e: NumberFormatException) {
                    "Please enter valid numbers for times".also { tvMessage.text = it }
                }
            } else {
                "Input cannot be empty".also { tvMessage.text = it }
            }
        }

        buttonCalc.setOnClickListener {
            val averageMinTemp = if (temperatureArrayminTemp.isNotEmpty()) {
                temperatureArrayminTemp.sum() / temperatureArrayminTemp.size
            } else {
                0f
            }

            val averageMaxTemp = if (temperatureArraymaxTemp.isNotEmpty()) {
                temperatureArraymaxTemp.sum() / temperatureArraymaxTemp.size
            } else {
                0f
            }

            val averageOverallTemp = (averageMinTemp + averageMaxTemp) / 2

            averageTemp.text = "Average Min Temp: ${String.format("%.2f", averageMinTemp)}°C\n" +
                    "Average Max Temp: ${String.format("%.2f", averageMaxTemp)}°C\n" +
                    "Average Overall Temp: ${String.format("%.2f", averageOverallTemp)}°C"

        }


        buttonNext.setOnClickListener {
            val intent = Intent(this, DetailedViewActivity::class.java)
            intent.putExtra("dateArray", dayArray.toTypedArray())
            intent.putExtra("timeArrayMinTemp", temperatureArrayminTemp.toFloatArray())
            intent.putExtra("timeArrayMaxTemp", temperatureArrayminTemp.toFloatArray())
            intent.putExtra("weatherConArray", weatherConArray.toTypedArray())
            startActivity(intent)
        }
    }
}


