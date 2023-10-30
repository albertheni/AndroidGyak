package com.tasty.recipesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import com.tasty.recipesapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Use a HandlerThread to create a background thread
        val handlerThread = HandlerThread("SplashHandlerThread", -10)
        handlerThread.start() // Create a Handler on the new HandlerThread
        val handler = Handler(handlerThread.looper)
        val SPLASH_TIME_OUT = 3000
        handler.postDelayed({
            // Navigate to MainActivity after the delay
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish() },
            SPLASH_TIME_OUT.toLong()) // Delay for SPLASH_TIME_OUT)

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SplashActivity", "onDestroy called!")
    }

    override fun onStart() {
        super.onStart()
        Log.d("SplashActivity", "onStart called!")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SplashActivity", "onStop called!")
    }

}