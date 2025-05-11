package com.nigdroid.coffeeshop.Activity

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.project1762.Helper.ManagmentCart
import com.nigdroid.coffeeshop.Domain.ItemsModel
import com.nigdroid.coffeeshop.Helper.ManagementFavourite
import com.nigdroid.coffeeshop.R
import com.nigdroid.coffeeshop.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private lateinit var managementCart: ManagmentCart
    private lateinit var managementFavourite: ManagementFavourite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        managementCart=ManagmentCart(this)
        managementFavourite = ManagementFavourite(this)

        bundle()
        initSizeList()

    }



    private fun initSizeList() {
        binding.apply {
            smallBtn.setOnClickListener {
                smallBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(0)

            }
            mediumBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
                largeBtn.setBackgroundResource(0)
            }
            largeBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
            }
        }
    }

    private fun bundle() {
        binding.apply {
            item = intent.getSerializableExtra("object") as ItemsModel

            Glide.with(this@DetailActivity)
                .load(item.picUrl[0])
                .into(binding.picMain)

            titleTxt.text = item.title
            descriptionTxt.text = item.description
            priceTxt.text = "$"+item.price
            ratingTxt.text = item.rating.toString()

            addToCartBtn.setOnClickListener {



                item.numberInCart=Integer.valueOf(numberItemTxt.text.toString())
                managementCart.insertItems(item)
            }
            backBtn.setOnClickListener {

//                startActivity(Intent(this@DetailActivity, MainActivity::class.java))

                finish()
            }

            plusCart.setOnClickListener {

                numberItemTxt.text=(item.numberInCart+1).toString()
                item.numberInCart++
            }

            minusCart.setOnClickListener {

                if (item.numberInCart > 0) {
                    numberItemTxt.text = (item.numberInCart - 1).toString()
                    item.numberInCart--
                }
            }

            val prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE)
            val editor = prefs.edit()


            // Restore background state if it was changed



//          var imgFav:ImageView=findViewById(R.id.img_fav)



          var img_fav: ImageView = findViewById(R.id.img_fav)
            img_fav.setOnClickListener {
                managementFavourite.insertItem(item)

                img_fav.setImageResource(R.drawable.img_8)

            }




        }
    }
}