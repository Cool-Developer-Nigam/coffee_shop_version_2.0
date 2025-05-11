package com.nigdroid.coffeeshop.Activity
//
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nigdroid.coffeeshop.R
//
//class checkoutActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_checkout)
//
//    }
//}


import android.content.Intent
//import android.os.Bundle
import android.widget.*
//import androidx.appcompat.app.AppCompatActivity

class CheckoutActivity : AppCompatActivity() {

    private lateinit var editAddress: EditText
    private lateinit var paymentOptions: RadioGroup
    private lateinit var buttonConfirmOrder: Button
    private lateinit var textTotalSummary: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)


        // View references
        editAddress = findViewById(R.id.editAddress)
        paymentOptions = findViewById(R.id.paymentOptions)
        buttonConfirmOrder = findViewById(R.id.buttonConfirmOrder)
        textTotalSummary = findViewById(R.id.textTotalSummary)

        // Optionally receive total from intent

        val totalAmount = intent.getDoubleExtra("total", 0.0)

        textTotalSummary.text = "Total: $${String.format("%.2f", totalAmount)}"

        buttonConfirmOrder.setOnClickListener {
            handleOrderConfirmation()
        }
    }

    private fun handleOrderConfirmation() {
        val address = editAddress.text.toString().trim()
        val selectedPaymentId = paymentOptions.checkedRadioButtonId

        if (address.isEmpty()) {
            Toast.makeText(this, "Please enter your delivery address", Toast.LENGTH_SHORT).show()
            return
        }

        if (selectedPaymentId == -1) {
            Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show()
            return
        }

        val paymentMethod = when (selectedPaymentId) {
            R.id.radioCard -> "Card"
            R.id.radioCash -> "Cash on Delivery"
            else -> "Unknown"
        }

        // Simulate order placement
        Toast.makeText(this, "Order placed via $paymentMethod!", Toast.LENGTH_LONG).show()

        // Optional: Navigate to confirmation screen
        val intent = Intent(this, OrderConfirmationActivity::class.java)
        intent.putExtra("ADDRESS", address)
        intent.putExtra("PAYMENT_METHOD", paymentMethod)
        intent.putExtra("TOTAL_AMOUNT", textTotalSummary.text.toString())
        startActivity(intent)

        // Optional: finish() to prevent back to checkout
    }
}
