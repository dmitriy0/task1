package com.example.task1

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class ReceiverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiver)

        val intentFilter = IntentFilter("myAction")
        val receiver = ReceiverExample()
        registerReceiver(receiver,intentFilter)

        val send = findViewById<Button>(R.id.send)

        send.setOnClickListener(View.OnClickListener {
            val intent = Intent("myAction")
            intent.putExtra("message", "its work")
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
            sendBroadcast(intent)
        })

    }
}