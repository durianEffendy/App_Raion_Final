package com.example.tes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CreateCard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_list)

        val btnCreateList = findViewById<Button>(R.id.save_button)
        val etTaskName = findViewById<EditText>(R.id.etTaskName)
        val etDescription = findViewById<EditText>(R.id.etDescription)

        btnCreateList.setOnClickListener {
            if (etTaskName.text.toString().trim(){it<=' '}.isNotEmpty()
                && etDescription.text.toString().trim(){it<=' '}.isNotEmpty()){

                var title = etTaskName.text
                var desc = etDescription.text

                DataObject.setData(title.toString(), desc.toString())
                val intent = Intent(this, TodoActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}