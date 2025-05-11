package com.nigdroid.coffeeshop.Activity
//
//import android.os.Bundle
import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nigdroid.coffeeshop.R
//
//class OrderConfirmationActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_order_confirmation)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//    }
//}
//package com.example.coffeeshop

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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
    }
}
