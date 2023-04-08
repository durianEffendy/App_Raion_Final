package com.example.tes

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoActivity: AppCompatActivity(){
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        val returnBtn = findViewById<FloatingActionButton>(R.id.fabReturnTodo)
        val addListBtn = findViewById<FloatingActionButton>(R.id.fabAddList)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        returnBtn.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        addListBtn.setOnClickListener {
            intent = Intent(this, CreateCard::class.java)
            startActivity(intent)
            finish()
        }
        recyclerView.adapter = Adapter(DataObject.getAllData())
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

}