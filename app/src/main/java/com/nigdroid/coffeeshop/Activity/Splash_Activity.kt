package com.nigdroid.coffeeshop.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.nigdroid.coffeeshop.databinding.ActivitySplashBinding

class Splash_Activity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sP = getSharedPreferences("MyPref", MODE_PRIVATE)
        val editval: String = sP.getString("name", "").toString()
        val editval2: String = sP.getString("phone", "").toString()
        val editval4: String = sP.getString("email", "").toString()
        val editval3: String = sP.getString("address", "").toString()



        binding.startBtn.setOnClickListener {


    if(editval.isEmpty()){

        startActivity(Intent(this@Splash_Activity, LoginActivity::class.java))
    }else{
        startActivity(Intent(this@Splash_Activity, MainActivity::class.java))
    }

        }
    }
}