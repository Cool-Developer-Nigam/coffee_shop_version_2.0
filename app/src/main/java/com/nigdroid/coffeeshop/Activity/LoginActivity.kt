package com.nigdroid.coffeeshop.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.nigdroid.coffeeshop.R

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)


      var  edtTxt : EditText = findViewById(R.id.edtTxt)
        var  edtTxt2 : EditText = findViewById(R.id.edtTxt2)
        var  edtTxt3 : EditText = findViewById(R.id.edtTxt3)
        var  edtTxt4 : EditText = findViewById(R.id.edtTxt4)

        var button3 : Button = findViewById(R.id.button3)

        button3.setOnClickListener {

            val name = edtTxt.getText().toString()
            val phone = edtTxt2.getText().toString()
            val email = edtTxt4.getText().toString()
            val address = edtTxt3.getText().toString()



            val sP = getSharedPreferences("MyPref", MODE_PRIVATE)
            val ed = sP.edit()
            ed.putString("name", name)
            ed.putString("phone", phone)
            ed.putString("email", email)
            ed.putString("address", address)
            ed.apply()

            if(!name.isEmpty() && !phone.isEmpty() && !email.isEmpty() && !address.isEmpty()){

                Toast.makeText(this@LoginActivity,"Your data is successfully saved..",Toast.LENGTH_SHORT).show()

                startActivity(Intent(this@LoginActivity, MainActivity::class.java))


            }
            else{

                Toast.makeText(this@LoginActivity,"Please fill all the fields..",Toast.LENGTH_SHORT).show()

            }



        }





    }
}