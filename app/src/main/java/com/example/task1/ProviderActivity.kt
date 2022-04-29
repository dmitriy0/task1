package com.example.task1

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProviderActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_provider)
        val values = ContentValues()
        values.put(ContentProviderExample.name,"hello")
        contentResolver.insert(ContentProviderExample.CONTENT_URI, values)

        val cursor: Cursor? = contentResolver.query(
            Uri.parse("content://com.example.task1/myTable"),
            null,
            null,
            null,
            null
        )

        if (cursor!!.moveToFirst()) {
            val strBuild = StringBuilder()
            while (!cursor.isAfterLast) {
                strBuild.append("""
      
    ${cursor.getString(cursor.getColumnIndex("id"))}-${cursor.getString(cursor.getColumnIndex("name"))}
    """.trimIndent())
                cursor.moveToNext()
            }
            Toast.makeText(this,strBuild,Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this,"fail",Toast.LENGTH_LONG).show()
        }

    }
}