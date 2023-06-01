package com.example.tasbeeh

import android.animation.Animator
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.tasbeeh.databinding.ActivityMainBinding

class StartingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        val animationForCard1 = AnimationUtils.loadAnimation(this, R.anim.card1_animation)
        binding.card1.startAnimation(animationForCard1)

        val animationForCard2 = AnimationUtils.loadAnimation(this, R.anim.card2_animation)
        binding.card2.startAnimation(animationForCard2)

        val animationForCard3 = AnimationUtils.loadAnimation(this, R.anim.card3_animation)
        binding.card3.startAnimation(animationForCard3)

        val animationForCard4 = AnimationUtils.loadAnimation(this, R.anim.card4_animation)
        binding.card4.startAnimation(animationForCard4)


        val anim = AnimationUtils.loadAnimation(this, R.anim.for_text_and_image_anim)
        binding.imageTasbeeh.startAnimation(anim)
        binding.textTasbeeh.startAnimation(anim)


        if (animationForCard1.fillBefore){
            binding.card1.setOnClickListener {
                val intent = Intent(this, TasbeehCounterActivity::class.java)
                startActivity(intent)
            }
        }

        if (animationForCard1.fillBefore){
            binding.card2.setOnClickListener {
                val intent = Intent(this, DhikrActivity::class.java)
                startActivity(intent)
            }
        }
        binding.card4.setOnClickListener {
            onShareButtonClick(binding.card4)
        }
        binding.card3.setOnClickListener {
            onRateAppButtonClick(binding.card3)
        }
    }

    private fun onShareButtonClick(view: View) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Sharing Example")
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome app!")
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }

    private fun onRateAppButtonClick(view: View) {
        val packageName = packageName
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
        } catch (e: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
        }
    }

}