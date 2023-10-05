package com.example.algroup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edt_password: EditText = findViewById(R.id.edt_password)
        val txt_subTitulo: TextView = findViewById(R.id.txt_subTitulo)
        val edt_codeRand: EditText = findViewById(R.id.edt_codeRandom)
        val txt_tiempoR: TextView = findViewById(R.id.txt_tiempoR)
        val btn_login: Button = findViewById(R.id.btn_login)
        val num = (Math.random() * (10000 - 1001) + 1001).toInt()
        txt_subTitulo.text = num.toString()




        btn_login.setOnClickListener {
            when (edt_password.text.toString()){
                "" -> {
                    Toast.makeText(this, "No has insertado la contraseña", Toast.LENGTH_SHORT).show()
                }
                "123"->{
                    if (num ==  edt_codeRand.text.toString().toInt()){
                            val login = Intent ( this, Principal_activity::class.java)
                            startActivity(login)
                    }else {
                            Toast.makeText(this, "El codigo ingresado/contraseña son incorrectos", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }


        val timer = object : CountDownTimer(100000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                txt_tiempoR.text = "Su codigo termina en: " + millisUntilFinished / 1000
            }

            override fun onFinish() {
                edt_codeRand.isEnabled = false
            }
        }.start()
    }
}