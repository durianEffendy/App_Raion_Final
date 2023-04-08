package com.example.tes

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.internal.ContextUtils.getActivity

private var clickButton : MediaPlayer? = null

class SettingsActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val pomodoroNum = findViewById<TextView>(R.id.pomodoroTimeSet)
        var pomoValue = intent.getIntExtra("POMODORO", 0)
        var lokalPomo = pomoValue
        pomodoroNum.text = pomoValue.toString()

        val SBNum = findViewById<TextView>(R.id.SBTimeSet)
        var SBValue = intent.getIntExtra("SB", 0)
        var lokalSB = SBValue
        SBNum.text = SBValue.toString()

        val LBNum = findViewById<TextView>(R.id.LBTimeSet)
        var LBValue = intent.getIntExtra("LB", 0)
        var lokalLB = LBValue
        LBNum.text = LBValue.toString()

        val btnAddPomo = findViewById<FloatingActionButton>(R.id.fabPodoAdd)
        val btnMinPomo = findViewById<FloatingActionButton>(R.id.fabPodoMin)
        val btnAddSB = findViewById<FloatingActionButton>(R.id.fabSBAdd)
        val btnMinSB = findViewById<FloatingActionButton>(R.id.fabSBMin)
        val btnAddLB = findViewById<FloatingActionButton>(R.id.fabLBAdd)
        val btnMinLB = findViewById<FloatingActionButton>(R.id.fabLBMin)

        val btnReturn = findViewById<FloatingActionButton>(R.id.btnReturn)

        val btnSave = findViewById<FloatingActionButton>(R.id.btnSave)



        btnAddPomo.setOnClickListener{
            clickButton?.start()
            btnSave.visibility = View.VISIBLE
            if (lokalPomo<99) {
                lokalPomo++
            }
            pomodoroNum.text = lokalPomo.toString()
        }

        btnMinPomo.setOnClickListener{
            clickButton?.start()
            btnSave.visibility = View.VISIBLE
            if(lokalPomo>1){
                lokalPomo--
            }
            pomodoroNum.text = lokalPomo.toString()
        }

        btnAddLB.setOnClickListener{
            clickButton?.start()
            btnSave.visibility = View.VISIBLE
            if(LBValue<99){
                lokalLB++
            }
            LBNum.text = lokalLB.toString()
        }

        btnMinLB.setOnClickListener{
            clickButton?.start()
            btnSave.visibility = View.VISIBLE
            if(lokalLB>1){
                lokalLB--
            }
            LBNum.text = lokalLB.toString()
        }

        btnAddSB.setOnClickListener{
            clickButton?.start()
            btnSave.visibility = View.VISIBLE
            if(lokalSB<99){
                lokalSB++
            }
            SBNum.text = lokalSB.toString()
        }

        btnMinSB.setOnClickListener{
            clickButton?.start()
            btnSave.visibility = View.VISIBLE
            if(lokalSB>1){
                lokalSB--
            }
            SBNum.text = lokalSB.toString()
        }

        btnSave.setOnClickListener{
            clickButton?.start()
            pomoValue = lokalPomo
            SBValue = lokalSB
            LBValue = lokalLB
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
            btnSave.visibility = View.INVISIBLE
        }

        btnReturn.setOnClickListener{
            clickButton?.start()
            val intent = Intent(this, MainActivity:: class.java)
            intent.putExtra("POMODORO_ret", pomoValue)
            intent.putExtra("SB_ret", SBValue)
            intent.putExtra("LB_ret", LBValue)
            startActivity(intent)
        }
    }
}