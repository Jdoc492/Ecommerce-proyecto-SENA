package com.example.algroup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class Splash_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        VerSplasScreen()
    }

    fun VerSplasScreen(){
        object : CountDownTimer(3000, 1000){
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                val intent = Intent(this@Splash_screen, MainActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }

        }.start()
    }
}
