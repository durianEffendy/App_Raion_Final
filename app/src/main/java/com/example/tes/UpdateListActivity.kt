package com.example.tes

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateListActivity:AppCompatActivity() {
    private lateinit var database: myDatabase
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updatelist)

        database= Room.databaseBuilder(
            applicationContext,myDatabase::class.java, "To_Do"
        ).build()

        val etTaskName = findViewById<EditText>(R.id.etTaskName)
        val etDescription = findViewById<EditText>(R.id.etDescription)

        val deleteBtn = findViewById<Button>(R.id.deleteBtn)
        val updateBtn = findViewById<Button>(R.id.updateBtn)

        val pos = intent.getIntExtra("id", -1)
        if(pos!=-1){
            val title = DataObject.getData(pos).title
            val priority = DataObject.getData(pos).priority
            etTaskName.setText(title)
            etDescription.setText(priority)

            deleteBtn.setOnClickListener {
                DataObject.deleteData(pos)

                myIntent()

            }

            updateBtn.setOnClickListener {
                DataObject.updateData(pos, etTaskName.text.toString(), etDescription.text.toString())

                myIntent()
            }
        }
    }
    fun myIntent(){
        val intent = Intent(this, TodoActivity::class.java)
        startActivity(intent)
        finish()
    }
}