package com.example.task1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import java.io.Console


class ReceiverExample : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        Toast.makeText(context, "Message: " + intent.getStringExtra("message"), Toast.LENGTH_LONG).show()

    }
}