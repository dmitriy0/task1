package com.example.task1

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class ProviderActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider)

        val getContacts = findViewById<Button>(R.id.button_contacts)
        val buttonAdd = findViewById<Button>(R.id.button_add)
        val buttonGet = findViewById<Button>(R.id.button_get)

        val textContacts = findViewById<TextView>(R.id.textView_contacts)
        val values = ContentValues()

        buttonAdd.setOnClickListener {
            values.put(ContentProviderExample.name,"hello")
            contentResolver.insert(ContentProviderExample.CONTENT_URI, values)
        }

        buttonGet.setOnClickListener {
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

        val list = ArrayList<String>()

        getContacts.setOnClickListener {
            val permissionStatus =
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)

            if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
                val cr = contentResolver
                val cur = cr.query(
                    ContactsContract.Contacts.CONTENT_URI,
                    null, null, null, null
                )

                if (cur?.count ?: 0 > 0) {
                    while (cur != null && cur.moveToNext()) {

                        val id = cur.getString(
                            cur.getColumnIndex(ContactsContract.Contacts._ID)
                        )

                        val name = cur.getString(
                            cur.getColumnIndex(
                                ContactsContract.Contacts.DISPLAY_NAME
                            )
                        )

                        if (cur.getInt(
                                cur.getColumnIndex(
                                    ContactsContract.Contacts.HAS_PHONE_NUMBER
                                )
                            ) > 0
                        ) {
                            val pCur = cr.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                arrayOf(id),
                                null
                            )
                            while (pCur!!.moveToNext()) {
                                val phoneNo = pCur.getString(
                                    pCur.getColumnIndex(
                                        ContactsContract.CommonDataKinds.Phone.NUMBER
                                    )
                                )
                                list.add("$name $phoneNo")
                            }
                            textContacts.text = list.toString()
                            pCur.close()
                        }
                    }
                }
                cur?.close()
            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.READ_CONTACTS),
                    100
                )
            }

        }

    }
}