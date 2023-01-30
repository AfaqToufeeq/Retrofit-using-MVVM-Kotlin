package app.developer.retrofitmvvm.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import app.developer.retrofitmvvm.R
import app.developer.retrofitmvvm.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    var splashTimeOut = 2500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashActive()
    }
    private fun splashActive() {
        Handler().postDelayed({
            val intent = Intent(this@SplashScreenActivity, ApiDataActivity::class.java)
            startActivity(intent)
            finish()
        }, splashTimeOut.toLong())
        val myAnim = AnimationUtils.loadAnimation(this, R.anim.mysplashanimation)
        binding.splash.startAnimation(myAnim)
    }
}