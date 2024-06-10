package com.charlie.imadpracticumexam

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailedViewActivity : AppCompatActivity() {

    private lateinit var dayDetails: TextView
    private lateinit var minTemp: TextView
    private lateinit var maxTemp: TextView
    private lateinit var weatherCon: TextView
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_view_screen)

        btnBack = findViewById(R.id.btnBack)
        dayDetails = findViewById(R.id.dayDetails)
        minTemp = findViewById(R.id.minTemp)
        maxTemp = findViewById(R.id.maxTemp)
        weatherCon = findViewById(R.id.weatherCon)

        val dayArray = intent.getStringArrayExtra("dayArray")?.toList() ?: emptyList()
        val temperatureArrayminTemp = intent.getFloatArrayExtra("temperatureArrayminTemp")?.toList() ?: emptyList()
        val temperatureArraymaxTemp = intent.getFloatArrayExtra("temperatureArraymaxTemp")?.toList() ?: emptyList()
        val weatherConArray = intent.getStringArrayExtra("weatherConArray")?.toList() ?: emptyList()

        val dayDetail = StringBuilder()
        for ((index, day) in dayArray.withIndex()) {
            dayDetail.append("Day ${index + 1}: $day \n")
        }

        val minTemperature = StringBuilder()
        for ((index, temp) in temperatureArrayminTemp.withIndex()) {
            minTemperature.append("min temp ${index + 1}: $temp \n")
        }

        val maxTemperature = StringBuilder()
        for ((index, temp) in temperatureArraymaxTemp.withIndex()) {
            maxTemperature.append("max temp ${index + 1}: $temp \n")
        }

        val weatherCondition = StringBuilder()
        for ((index, condition) in weatherConArray.withIndex()) {
            weatherCondition.append("conditions ${index + 1}: $condition \n")
        }

        dayDetails.text = dayDetail.toString()
        minTemp.text = minTemperature.toString()
        maxTemp.text = maxTemperature.toString()
        weatherCon.text = weatherCondition.toString()

        btnBack.setOnClickListener { finish() }
    }
}

