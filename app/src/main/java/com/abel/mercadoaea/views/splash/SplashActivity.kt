package com.abel.mercadoaea.views.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.abel.mercadoaea.R
import com.abel.mercadoaea.databinding.ActivitySplashBinding
import com.abel.mercadoaea.views.base.BaseActivity
import com.abel.mercadoaea.views.main.MainActivity

class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        initListener()
    }

    private fun initListener() {
        binding.floatingActionButton.setOnClickListener {
            goToMainActivity()
        }

    }

    private fun goToMainActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }, 1550L)
    }
}