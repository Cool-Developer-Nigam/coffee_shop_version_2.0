package com.nigdroid.coffeeshop.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nigdroid.coffeeshop.Adapter.ItemsListCategoryAdapter
import com.nigdroid.coffeeshop.R
import com.nigdroid.coffeeshop.ViewModel.MainViewModel
import com.nigdroid.coffeeshop.databinding.ActivityItemsListBinding


class ItemsListActivity : AppCompatActivity() {
    lateinit var binding:ActivityItemsListBinding
    private val viewModel=MainViewModel()
    private var id:String=""
    private var title:String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityItemsListBinding.inflate(layoutInflater)

        setContentView(binding.root)

        getBundle()

        initList()

    }

    private fun initList() {
        binding.apply{

            progressBar4.visibility=View.VISIBLE
            viewModel.loadItems(id).observe(this@ItemsListActivity,Observer{listView.layoutManager=LinearLayoutManager(this@ItemsListActivity,LinearLayoutManager.VERTICAL,false)
            listView.adapter=ItemsListCategoryAdapter(it)
            progressBar4.visibility=View.GONE
            })
            backBtn.setOnClickListener {finish()}

        }
    }

    private fun getBundle(){
       id=intent.getStringExtra("id")!!
        title=intent.getStringExtra("title")!!

     binding.categoryTxt.text=title


    }
}