package com.example.task1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi

class ServiceActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        val startButton = findViewById<Button>(R.id.start)
        val stopButton = findViewById<Button>(R.id.stop)
        val intent = Intent(this, ServiceExample::class.java)

        startButton.setOnClickListener(View.OnClickListener {
            this.startService(intent)

        })
        stopButton.setOnClickListener(View.OnClickListener {
            this.stopService(intent)
        })

    }
}