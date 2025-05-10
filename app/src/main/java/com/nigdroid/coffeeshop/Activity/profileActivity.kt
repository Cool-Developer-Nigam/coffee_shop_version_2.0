//package com.nigdroid.coffeeshop.Activity
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.LinearLayout
//import android.widget.TextView
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.nigdroid.coffeeshop.Activity.FavouriteActivity
//import com.nigdroid.coffeeshop.Activity.LoginActivity
//import com.nigdroid.coffeeshop.R
//import com.nigdroid.coffeeshop.databinding.ActivityProfileBinding
//
//import android.app.Activity
//import android.content.Context
//import android.content.SharedPreferences
//import android.net.Uri
//import android.provider.MediaStore
//import android.widget.Button
//import android.widget.ImageView
//import androidx.activity.result.ActivityResult
//import androidx.activity.result.ActivityResultCallback
//import androidx.activity.result.ActivityResultLauncher
//import androidx.activity.result.contract.ActivityResultContracts
//import java.io.*
//
//class profileActivity : AppCompatActivity() {
//
//    private lateinit var selectImageButton: LinearLayout
//    private lateinit var imageView: ImageView
//
//    // Using ActivityResultLauncher for modern activity results
//    private val selectImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            val data: Intent? = result.data
//            data?.data?.let { imageUri ->
//                // Store the image internally
//                val storedImageUri = storeImageInternal(this, imageUri)
//                if (storedImageUri != null) {
//                    // Save the URI of the internally stored image persistently
//                    saveImageUri(this, storedImageUri)
//
//                    // Display the internally stored image
//                    displayStoredImage(this)
//                } else {
//                    // Handle the case where internal storage failed (optional: show a Toast)
//                }
//            }
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_profile)
//
//        selectImageButton = findViewById(R.id.edit_profile_button) // Assuming your Button has this ID
//        imageView = findViewById(R.id.profile_picture) // Assuming your ImageView has this ID
//
//        // Set up the click listener for the button to open the gallery
//        selectImageButton.setOnClickListener {
//            openGallery()
//        }
//
//        // Display the previously stored image when the activity is created
//        displayStoredImage(this)
//
//        val sP = getSharedPreferences("MyPref", MODE_PRIVATE)
//
//        val name = sP.getString("name", "")
//        val phone = sP.getString("phone", "")
//        val email = sP.getString("email", "")
//        val address = sP.getString("address", "")
//
//        val words=name?.split(" ")
//        val firstWord=words?.get(0)
//
//      var username_text : TextView = findViewById(R.id.username_text)
//        username_text.text = "Hello ! "+firstWord
//
//        var email_text : TextView = findViewById(R.id.email_text)
//        email_text.text = email
//
//        var contact_info_text : TextView = findViewById(R.id.contact_info_text)
//        contact_info_text.text ="Phone no. : "+ phone
//
//        var contact_info_text2 : TextView = findViewById(R.id.contact_info_text2)
//        contact_info_text2.text ="Email : "+ email
//
//        var about_me_text : TextView = findViewById(R.id.about_me_text)
//        about_me_text.text ="You Name is : "+ name
//
//        var achievements_placeholder : TextView = findViewById(R.id.achievements_placeholder)
//        achievements_placeholder.text ="Address : "+ address
//
//
//        var logout_button : TextView = findViewById(R.id.logout_button)
//
//        logout_button.setOnClickListener {
//
//            val sP = getSharedPreferences("MyPref", MODE_PRIVATE)
//            val editor = sP.edit()
//            editor.clear()
//            editor.apply()
//
//            startActivity(Intent(this@profileActivity, LoginActivity::class.java))
//
//            Toast.makeText(this@profileActivity,"Logout Successfully..",Toast.LENGTH_SHORT).show()
//
//        }
//
//        var fav_link : TextView = findViewById(R.id.fav_link)
//        fav_link.setOnClickListener {
//
//            startActivity(Intent(this@profileActivity, FavouriteActivity::class.java))
//
//
//            Toast.makeText(this@profileActivity,"You have entered to Favourites",Toast.LENGTH_SHORT).show()
//        }
//
//        var img_back : LinearLayout = findViewById(R.id.img_back)
//
//        img_back.setOnClickListener {
//            finish()
//        }
//
//
//
//
//
//    }
//    private fun openGallery() {
//        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        selectImageLauncher.launch(galleryIntent)
//    }
//    private fun storeImageInternal(context: Context, imageUri: Uri): Uri? {
//        var outputStream: FileOutputStream? = null
//        var inputStream: InputStream? = null
//        try {
//            // Create a file in your internal files directory
//            val fileName = "permanent_selected_image.jpg" // Use a consistent filename
//            val internalFile = File(context.filesDir, fileName)
//
//            // Use a more robust way to copy streams
//            inputStream = context.contentResolver.openInputStream(imageUri)
//            outputStream = FileOutputStream(internalFile)
//
//            inputStream?.copyTo(outputStream)
//
//            // Return the URI of the stored image
//            return Uri.fromFile(internalFile)
//
//        } catch (e: IOException) {
//            e.printStackTrace()
//            return null
//        } finally {
//            try {
//                outputStream?.close()
//                inputStream?.close()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//        }
//    }
//    private fun saveImageUri(context: Context, uri: Uri) {
//        val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
//        with(sharedPreferences.edit()) {
//            putString("stored_image_uri", uri.toString())
//            apply() // Use apply() for asynchronous saving
//        }
//    }
//    private fun displayStoredImage(context: Context) {
//        val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
//        val storedUriString = sharedPreferences.getString("stored_image_uri", null)
//
//        if (storedUriString != null) {
//            try {
//                val storedUri = Uri.parse(storedUriString)
//                imageView.setImageURI(storedUri)
//            } catch (e: Exception) {
//                e.printStackTrace()
//                // Handle potential issues with parsing or loading the URI
//            }
//        } else {
//            // Optionally set a default image if no image is stored
//            // imageView.setImageResource(R.drawable.default_image)
//        }
//    }
//}
package com.nigdroid.coffeeshop.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nigdroid.coffeeshop.Activity.FavouriteActivity
import com.nigdroid.coffeeshop.Activity.LoginActivity
import com.nigdroid.coffeeshop.R
import com.nigdroid.coffeeshop.databinding.ActivityProfileBinding

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import java.io.*

class profileActivity : AppCompatActivity() {

    private lateinit var selectImageButton: LinearLayout
    private lateinit var imageView: ImageView

    // Using ActivityResultLauncher for modern activity results
    private val selectImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            data?.data?.let { imageUri ->
                // Store the image internally
                val storedImageUri = storeImageInternal(this, imageUri)
                if (storedImageUri != null) {
                    // Save the URI of the internally stored image persistently
                    saveImageUri(this, storedImageUri)

                    // Display the internally stored image
                    displayStoredImage(this)
                } else {
                    // Handle the case where internal storage failed (optional: show a Toast)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        selectImageButton = findViewById(R.id.edit_profile_button) // Assuming your Button has this ID
        imageView = findViewById(R.id.profile_picture) // Assuming your ImageView has this ID

        // Set up the click listener for the button to open the gallery
        selectImageButton.setOnClickListener {
            openGallery()
        }

        // Display the previously stored image when the activity is created
        displayStoredImage(this)

        val sP = getSharedPreferences("MyPref", MODE_PRIVATE)

        val name = sP.getString("name", "")
        val phone = sP.getString("phone", "")
        val email = sP.getString("email", "")
        val address = sP.getString("address", "")

        val words=name?.split(" ")
        val firstWord=words?.get(0)

        var username_text : TextView = findViewById(R.id.username_text)
        username_text.text = "Hi, "+firstWord

        var email_text : TextView = findViewById(R.id.email_text)
        email_text.text = email

        var contact_info_text : TextView = findViewById(R.id.contact_info_text)
        contact_info_text.text ="Phone no. : "+ phone

        var contact_info_text2 : TextView = findViewById(R.id.contact_info_text2)
        contact_info_text2.text ="Email : "+ email

        var about_me_text : TextView = findViewById(R.id.about_me_text)
        about_me_text.text ="You Name is : "+ name

        var achievements_placeholder : TextView = findViewById(R.id.achievements_placeholder)
        achievements_placeholder.text ="Address : "+ address


        var logout_button : TextView = findViewById(R.id.logout_button)

        logout_button.setOnClickListener {

            // Clear user login data from SharedPreferences
            val sP_login = getSharedPreferences("MyPref", MODE_PRIVATE)
            val editor_login = sP_login.edit()
            editor_login.clear()
            editor_login.apply()

            // Reset profile picture
            resetProfilePicture(this)

            // Reset other information (optional, depends on if these are also persisted)
            // If "name", "phone", "email", "address" are only stored in "MyPref",
            // clearing "MyPref" above already resets them for the next login.
            // If they are displayed based on the current session, you might want to
            // clear the TextViews here, but since you're navigating to LoginActivity,
            // the profileActivity will be finished, so this isn't strictly necessary
            // for the current implementation.
            // If you had other UI elements displaying persistent data not in "MyPref",
            // you would reset them here.

            startActivity(Intent(this@profileActivity, LoginActivity::class.java))

            Toast.makeText(this@profileActivity,"Logout Successfully..",Toast.LENGTH_SHORT).show()

            // Finish the current profile activity so the user can't go back to it after logging out
            finish()
        }

        var fav_link : TextView = findViewById(R.id.fav_link)
        fav_link.setOnClickListener {

            startActivity(Intent(this@profileActivity, FavouriteActivity::class.java))


            Toast.makeText(this@profileActivity,"You have entered to Favourites ",Toast.LENGTH_SHORT).show()
        }

        var cart_link : TextView = findViewById(R.id.cart_link)
        cart_link.setOnClickListener {
            startActivity(Intent(this@profileActivity, CartActivity::class.java))
            Toast.makeText(this@profileActivity,"You have entered to Cart ",Toast.LENGTH_SHORT).show()
        }



        var img_back : LinearLayout = findViewById(R.id.img_back)

        img_back.setOnClickListener {
            finish()
        }





    }
    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        selectImageLauncher.launch(galleryIntent)
    }
    private fun storeImageInternal(context: Context, imageUri: Uri): Uri? {
        var outputStream: FileOutputStream? = null
        var inputStream: InputStream? = null
        try {
            // Create a file in your internal files directory
            val fileName = "permanent_selected_image.jpg" // Use a consistent filename
            val internalFile = File(context.filesDir, fileName)

            // Use a more robust way to copy streams
            inputStream = context.contentResolver.openInputStream(imageUri)
            outputStream = FileOutputStream(internalFile)

            inputStream?.copyTo(outputStream)

            // Return the URI of the stored image
            return Uri.fromFile(internalFile)

        } catch (e: IOException) {
            e.printStackTrace()
            return null
        } finally {
            try {
                outputStream?.close()
                inputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    private fun saveImageUri(context: Context, uri: Uri) {
        val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("stored_image_uri", uri.toString())
            apply() // Use apply() for asynchronous saving
        }
    }
    private fun displayStoredImage(context: Context) {
        val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        val storedUriString = sharedPreferences.getString("stored_image_uri", null)

        if (storedUriString != null) {
            try {
                val storedUri = Uri.parse(storedUriString)
                imageView.setImageURI(storedUri)
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle potential issues with parsing or loading the URI
            }
        } else {
            // Optionally set a default image if no image is stored
            // imageView.setImageResource(R.drawable.default_image) // Uncomment and provide a default image drawable
        }
    }

    // New function to reset the profile picture
    private fun resetProfilePicture(context: Context) {
        // Remove the stored image URI from SharedPreferences
        val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            remove("stored_image_uri")
            apply()
        }

        // Delete the internally stored image file
        val fileName = "permanent_selected_image.jpg"
        val internalFile = File(context.filesDir, fileName)
        if (internalFile.exists()) {
            internalFile.delete()
        }

        // Set the ImageView to a default image or clear it
        // Option 1: Set a default image
        imageView.setImageResource(R.drawable.btn_5) // Replace with your default image drawable

        // Option 2: Clear the ImageView (make it blank)
        // imageView.setImageDrawable(null)
    }
}