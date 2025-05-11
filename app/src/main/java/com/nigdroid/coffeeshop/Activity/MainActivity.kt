package com.nigdroid.coffeeshop.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nigdroid.coffeeshop.Adapter.CategoryAdapter
import com.nigdroid.coffeeshop.Adapter.PopularAdapter
import com.nigdroid.coffeeshop.R
import com.nigdroid.coffeeshop.ViewModel.MainViewModel
import com.nigdroid.coffeeshop.databinding.ActivityMainBinding
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()

        binding= ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initBanner()
        initCatagory()
        initPopular()

        initBottomMenu()


    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity() // This will finish all activities and exit the app
    }


    private fun initBottomMenu() {


        binding.exploreBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainActivity::class.java))


        }

        binding.cartBtn.setOnClickListener {

            startActivity(Intent(this@MainActivity,CartActivity::class.java))
            binding.imgCart.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN)
        }

        binding.profileBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity,profileActivity::class.java))
        }

        binding.favouriteBtn.setOnClickListener {

            startActivity(Intent(this@MainActivity,FavouriteActivity::class.java))

        }
        binding.contactUs.setOnClickListener { 
            
            startActivity(Intent(this@MainActivity, About_us::class.java))
            
            
        }

    }


    private fun initBanner() {
        binding.progressBar.visibility= View.VISIBLE
        viewModel.loadBanner().observeForever {

            Glide.with(this@MainActivity)
                .load(it[0].url)
                .into(binding.imgBanner)
            binding.progressBar.visibility= View.GONE

        }
        viewModel.loadBanner()
    }

    private fun initCatagory() {
        binding.progressBar2.visibility= View.VISIBLE
        viewModel.loadCatagory().observeForever {

            binding.recyclerView1.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            binding.recyclerView1.adapter=CategoryAdapter(it)
            binding.progressBar2.visibility= View.GONE

        }
        viewModel.loadCatagory()
    }

    private fun initPopular() {
        binding.progressBar3.visibility= View.VISIBLE
        viewModel.loadPopular().observeForever {

            binding.recyclerView2.layoutManager=GridLayoutManager(this,2)
            binding.recyclerView2.adapter=PopularAdapter(it)
            binding.progressBar3.visibility= View.GONE


        }

        viewModel.loadPopular()


    }



}