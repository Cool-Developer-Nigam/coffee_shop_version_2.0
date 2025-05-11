package com.nigdroid.coffeeshop.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nigdroid.coffeeshop.R

class OrderConfirmationActivity : AppCompatActivity() {

    private lateinit var textThankYou: TextView
    private lateinit var textSummary: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)

        textThankYou = findViewById(R.id.textThankYou)
        textSummary = findViewById(R.id.textSummary)

        // Get passed data from intent
        val address = intent.getStringExtra("ADDRESS")
        val paymentMethod = intent.getStringExtra("PAYMENT_METHOD")
        val totalAmount = intent.getStringExtra("TOTAL_AMOUNT")

        // Set thank you message
        textThankYou.text = "Thank You for Your Order!"

        // Set order summary
        textSummary.text = """
            Delivery Address:
            $address
            
            Payment Method:
            $paymentMethod
            
            Total:
            $totalAmount
        """.trimIndent()


        var call_us : LinearLayout = findViewById(R.id.call_us)

        call_us.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val idial = Intent(Intent.ACTION_DIAL)

                idial.setData(Uri.parse("tel:+919861703761"))
                startActivity(idial)
            }
        })


    }
}
