package com.nigdroid.coffeeshop.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1762.Helper.ManagmentCart
import com.nigdroid.coffeeshop.Adapter.CartAdapter
import com.nigdroid.coffeeshop.R
import com.nigdroid.coffeeshop.databinding.ActivityCartBinding
import com.uilover.project195.Helper.ChangeNumberItemsListener

class CartActivity : AppCompatActivity() {

    lateinit var binding:ActivityCartBinding
    lateinit var managmentCart: ManagmentCart
    private var tax:Double=0.0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managmentCart= ManagmentCart(this)

        calculateCart()
        setVariable()
        initCartList()


       binding.proceedToCheckout.setOnClickListener {

           intent= Intent(this,CheckoutActivity::class.java)
           startActivity(intent.putExtra("total",calculateCart()))

       }

    }

    private fun initCartList() {
        binding.apply {

            listView.layoutManager=LinearLayoutManager(this@CartActivity,LinearLayoutManager.VERTICAL,false)

            listView.adapter=CartAdapter(managmentCart.getListCart(),this@CartActivity,object : ChangeNumberItemsListener{

                override fun onChanged() {
                    calculateCart()
                }

            })
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener {


            finish()
        }
    }

    private fun calculateCart() :Double {
        val percentTax:Double=0.02
        val delivery=15
        tax=Math.round((managmentCart.getTotalFee()*percentTax)*100)/100.0
        val total=Math.round((managmentCart.getTotalFee()+tax+delivery)*100)/100
        val itemTotal=Math.round(managmentCart.getTotalFee()*100)/100

        binding.apply{
            totalFeeTxt.text="$itemTotal"
            taxTxt.text="$$tax"
            deliveryTxt.text="$$delivery"
            totalTxt.text="$$total"
        }

        return total.toDouble()
    }
}