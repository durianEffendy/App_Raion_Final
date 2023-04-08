package com.example.tes

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.media.audiofx.Equalizer.Settings
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var startBell : MediaPlayer? = null
    private var restBell : MediaPlayer? = null
    private var clickButton : MediaPlayer? = null
    private lateinit var timer : CountDownTimer
    private var WORK_TIME= 15
    private var SHORT_REST_TIME = 5
    private var LONG_REST_TIME = 10

    val trivias = arrayOf(trivia1, trivia2, trivia3, trivia4, trivia5)


    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var iterasi = 0

        startBell = MediaPlayer.create(this, R.raw.startbell)
        startBell?.setVolume(0.8f, 0.8f)

        restBell = MediaPlayer.create(this, R.raw.restbell)
        restBell?.setVolume(0.8f, 0.8f)

        clickButton = MediaPlayer.create(this, R.raw.button)

        var isRunning = false

        WORK_TIME = intent.getIntExtra("POMODORO_ret", WORK_TIME)
        SHORT_REST_TIME = intent.getIntExtra("SB_ret", SHORT_REST_TIME)
        LONG_REST_TIME = intent.getIntExtra("LB_ret", LONG_REST_TIME)

        val playBtn = findViewById<FloatingActionButton>(R.id.fabPlay)
        val resetBtn = findViewById<FloatingActionButton>(R.id.fabReset)
        val pauseBtn = findViewById<FloatingActionButton>(R.id.fabPause)
        val displayTimer = findViewById<TextView>(R.id.tvTimer)
        val text = findViewById<TextView>(R.id.tvText)
        val settingsBtn = findViewById<Button>(R.id.btnSettings)
        val progressBar = findViewById<ProgressBar>(R.id.ProgressTimer)
        val imageTrivia = findViewById<ImageView>(R.id.imageTrivia)
        val textTrivia = findViewById<TextView>(R.id.triviaText)
        val restTimer = findViewById<TextView>(R.id.restTimer)
        val todoBtn = findViewById<Button>(R.id.btnTodo)

        todoBtn.setOnClickListener {
            val intent = Intent(this, TodoActivity:: class.java)
            startActivity(intent)
            finish()
        }

        fun openSettings(){
            val intent = Intent(this, SettingsActivity:: class.java)
            intent.putExtra("POMODORO", WORK_TIME)
            intent.putExtra("SB", SHORT_REST_TIME)
            intent.putExtra("LB", LONG_REST_TIME)
            startActivity(intent)
        }

        settingsBtn.setOnClickListener {
            clickButton?.start()
            openSettings()
        }

        fun checkState():Int{
            if (iterasi!=1){
                startBell?.start()
            }

            if (iterasi%2==0){
                imageTrivia.visibility = View.VISIBLE
                imageTrivia.setImageResource(trivias[iterasi/2-1].image)
                textTrivia.visibility = View.VISIBLE
                textTrivia.text = trivias[iterasi/2-1].teks
                displayTimer.visibility = View.INVISIBLE
                restTimer.visibility = View.VISIBLE
            }else{
                displayTimer.visibility = View.VISIBLE
                imageTrivia.visibility = View.INVISIBLE
                textTrivia.visibility = View.INVISIBLE
                restTimer.visibility = View.INVISIBLE
            }

            return if (iterasi==8){
                text.text = "Finally, Long Break!"
                progressBar.max = LONG_REST_TIME
                progressBar.progress = LONG_REST_TIME
                LONG_REST_TIME

            }else if (iterasi%2==0){
                progressBar.max = SHORT_REST_TIME
                progressBar.progress = SHORT_REST_TIME
                text.text = "It's Break Time!"
                SHORT_REST_TIME

            }else{
                text.text = "POMODORO!"
                progressBar.max = WORK_TIME
                progressBar.progress = WORK_TIME
                WORK_TIME
            }
        }

        displayTimer.text = (WORK_TIME).toString()

        fun resetProgress(){
            progressBar.max = 10
            progressBar.progress = 10
        }

        progressBar.max = 100
        progressBar.progress = 100

        fun displayTime(timeLeft: Long){
            val minutes = (timeLeft/60000)
            val seconds = (timeLeft/1000%60)
            if (iterasi%2==0){
                if (minutes<=0){
                    restTimer.text = String.format("%d", seconds)
                }else{
                    restTimer.text = String.format("%2d : %02d", minutes, seconds)
                }
            }else{
                if (minutes<=0){
                    displayTimer.text = String.format("%d", seconds)
                }else{
                    displayTimer.text = String.format("%2d : %02d", minutes, seconds)
                }
            }

        }

        fun stopTimer(){
            timer.cancel()
            isRunning = false
        }

        var mTimeLeftInMillis = WORK_TIME

        fun playVisibility(){
            playBtn.visibility = View.INVISIBLE
            resetBtn.visibility = View.INVISIBLE
            settingsBtn.visibility = View.INVISIBLE
            pauseBtn.visibility = View.VISIBLE
            todoBtn.visibility = View.INVISIBLE
        }

        fun pauseVisibility(){
            playBtn.visibility = View.VISIBLE
            resetBtn.visibility = View.VISIBLE
            settingsBtn.visibility = View.VISIBLE
            pauseBtn.visibility = View.INVISIBLE
            todoBtn.visibility = View.VISIBLE
        }

        fun resetTimer() {
            iterasi = 0
            displayTimer.text = WORK_TIME.toString()
            mTimeLeftInMillis = WORK_TIME
            text.text = "Welcome!"
            pauseVisibility()
            displayTimer.visibility = View.VISIBLE
            imageTrivia.visibility = View.INVISIBLE
            textTrivia.visibility = View.INVISIBLE
            restTimer.visibility = View.INVISIBLE

        }

        fun startTimer(){

            timer = object : CountDownTimer (mTimeLeftInMillis.toLong() , 1000){
                override fun onTick(millisUntilFinished: Long) {
                    isRunning = true
                    mTimeLeftInMillis = millisUntilFinished.toInt()
                    displayTime(millisUntilFinished)
                    progressBar.setProgress((millisUntilFinished/1000).toInt(),true)
                }

                override fun onFinish() {

                    iterasi++
                    if(iterasi<=8){
                        mTimeLeftInMillis = (checkState() * 1000 +500)
                        startTimer()
                    }else{
                        pauseVisibility()
                        restBell?.start()
                        resetTimer()
                        text.text = "Awesome!"
                    }
                }

            }.start()
        }

        resetBtn.setOnClickListener {
            clickButton?.start()
            resetProgress()
            resetTimer()
        }

        playBtn.setOnClickListener{
            clickButton?.start()
            startTimer()
            playVisibility()

        }

        pauseBtn.setOnClickListener {
            clickButton?.start()
            stopTimer()
            pauseVisibility()
        }
    }
}