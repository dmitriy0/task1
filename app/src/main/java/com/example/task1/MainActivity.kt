package com.example.task1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonService = findViewById<Button>(R.id.button_service)
        val buttonReceiver = findViewById<Button>(R.id.button_receiver)
        val buttonProvider = findViewById<Button>(R.id.button_provider)

        buttonService.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,ServiceActivity::class.java)
            startActivity(intent)
        })

        buttonReceiver.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ReceiverActivity::class.java)
            startActivity(intent)
        })

        buttonProvider.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ProviderActivity::class.java)
            startActivity(intent)
        })
    }
}