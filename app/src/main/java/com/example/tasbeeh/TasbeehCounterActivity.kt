package com.example.tasbeeh

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.example.tasbeeh.databinding.ActivityMainBinding
import com.example.tasbeeh.databinding.ActivityTasbeehCounterBinding

class TasbeehCounterActivity : AppCompatActivity() {
    var count = 0
    lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: ActivityTasbeehCounterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTasbeehCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("myCaych", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        count = sharedPreferences.getInt("count", 0)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                )

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding.btnBack.setOnClickListener {
            onBackPressed()
            finish()
        }

        binding.btnClick.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.onclicksound)
            mediaPlayer.start()
            val scaleAnimationForButton =
                AnimationUtils.loadAnimation(this, R.anim.scale_anim_for_btnclick)
            binding.btnClick.startAnimation(scaleAnimationForButton)
            count++
            binding.textView.text = count.toString()
            editor.putInt("count", count)
            editor.apply()
            if (count % 100 == 0) {
                val mediaPlayer1 = MediaPlayer.create(this, R.raw.beep_sound)
                mediaPlayer.stop()
                mediaPlayer1.start()
            }
        }

        binding.btnReset.setOnClickListener {
            count = 0
            binding.textView.text = "0"
        }
    }
}