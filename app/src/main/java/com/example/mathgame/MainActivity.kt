package com.example.mathgame

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var random: Random
    private var result:Double? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var next = findViewById<Button>(R.id.button1)
        var myResult = findViewById<EditText>(R.id.myResult)
        var score = findViewById<TextView>(R.id.score)
        var score2 = findViewById<TextView>(R.id.score2)
        var count = 0
        var uncount = 0
        random = Random
        setElements()
        next.setOnClickListener {
            val myRes = myResult.text.toString()
            if (myRes.isEmpty()){
                Toast.makeText(this, "Please write answer", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val userAnswer = myRes.toDoubleOrNull()
            if (userAnswer != null && result != null) {
                val epsil = 0.1
                val dif = abs(userAnswer - result!!)
                if (dif <= epsil) {
                    Toast.makeText(this, "True", Toast.LENGTH_SHORT).show()
                    count++
                } else {
                    Toast.makeText(this, "False", Toast.LENGTH_SHORT).show()
                    uncount++
                }
            }
            score.text = count.toString()
            score2.text = uncount.toString()
            myResult.setText("")
            setElements()
        }


    }
    private fun setElements() {
        var num1 = findViewById<TextView>(R.id.number01)
        var num2 = findViewById<TextView>(R.id.number02)
        var elem = findViewById<TextView>(R.id.element)
        var son1 = random.nextInt(100)
        var son2 = random.nextInt(100)
        num1.text = son1.toString()
        num2.text = son2.toString()
        val n = random.nextInt(4)
        when(n) {
            0 -> {
                elem.text = "+"
                result = (son1+son2).toDouble()

            }
            1 -> {
                elem.text = "-"
                result = (son1-son2).toDouble()
            }
            2 -> {
                elem.text = "*"
                result = (son1*son2).toDouble()
            }
            3 -> {
                elem.text = "/"
                if (son2 != 0) {
                    result = son1.toDouble() / son2
                } else {
                    result = 0.0
                }
            }
        }
    }
}
