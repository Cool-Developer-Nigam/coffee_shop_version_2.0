
package com.nigdroid.coffeeshop.Activity

import android.app.Activity
import android.app.AlertDialog // Import AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory // For displaying the image in the dialog
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.nigdroid.coffeeshop.R
import com.nigdroid.coffeeshop.databinding.ActivityProfileBinding
import java.io.*

class profileActivity : AppCompatActivity() {

    private lateinit var selectImageButton: LinearLayout
    private lateinit var imageView: ImageView

    // Using ActivityResultLauncher for modern activity results
    private val selectImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            data?.data?.let { imageUri ->
                // **SHOW THE CONFIRMATION DIALOG HERE**
                showConfirmationDialog(imageUri)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        selectImageButton = findViewById(R.id.edit_profile)
        imageView = findViewById(R.id.profile_picture)

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

        val words = name?.split(" ")
        val firstWord = words?.get(0)

        var username_text: TextView = findViewById(R.id.username_text)
        username_text.text = "Hi, $firstWord" // Using string templates

        var email_text: TextView = findViewById(R.id.email_text)
        email_text.text = email

        var contact_info_text: TextView = findViewById(R.id.contact_info_text)
        contact_info_text.text = "Phone no. : $phone"

        var contact_info_text2: TextView = findViewById(R.id.contact_info_text2)
        contact_info_text2.text = "Email : $email"

        var about_me_text: TextView = findViewById(R.id.about_me_text)
        about_me_text.text = "You Name is : $name"

        var achievements_placeholder: TextView = findViewById(R.id.achievements_placeholder)
        achievements_placeholder.text = "Address : $address"

        var logout_button: Button = findViewById(R.id.logout_button)

        logout_button.setOnClickListener {
            // Clear user login data from SharedPreferences
            val sP_login = getSharedPreferences("MyPref", MODE_PRIVATE)
            val editor_login = sP_login.edit()
            editor_login.clear()
            editor_login.apply()

            // Reset profile picture
            resetProfilePicture(this)

            startActivity(Intent(this@profileActivity, LoginActivity::class.java))

            Toast.makeText(this@profileActivity, "Logout Successfully..", Toast.LENGTH_SHORT).show()

            // Finish the current profile activity so the user can't go back to it after logging out
            finish()
        }

        var fav_link: TextView = findViewById(R.id.fav_link)
        fav_link.setOnClickListener {
            startActivity(Intent(this@profileActivity, FavouriteActivity::class.java))
            Toast.makeText(this@profileActivity, "You have entered to Favourites ", Toast.LENGTH_SHORT).show()
        }

        var cart_link: TextView = findViewById(R.id.cart_link)
        cart_link.setOnClickListener {
            startActivity(Intent(this@profileActivity, CartActivity::class.java))
            Toast.makeText(this@profileActivity, "You have entered to Cart ", Toast.LENGTH_SHORT).show()
        }

        var img_back: LinearLayout = findViewById(R.id.img_back)

        img_back.setOnClickListener {
            finish()
        }
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        selectImageLauncher.launch(galleryIntent)
    }

    // New function to show the confirmation dialog
    private fun showConfirmationDialog(imageUri: Uri) {
        AlertDialog.Builder(this)
            .setTitle("Confirm Profile Picture")
            .setMessage("Do you want to set this image as your profile picture?")
            .setPositiveButton("Yes") { dialog, _ ->
                // User confirmed, proceed to store and display the image
                val storedImageUri = storeImageInternal(this, imageUri)
                if (storedImageUri != null) {
                    saveImageUri(this, storedImageUri)
                    displayStoredImage(this)
                } else {
                    Toast.makeText(this, "Internal storage failed", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                // User cancelled, do nothing
                dialog.cancel()
            }
            // Optionally add a neutral button or show a preview
            // .setNeutralButton("Preview") { dialog, _ ->
            //     showPreviewDialog(imageUri) // Implement showPreviewDialog if needed
            // }
            .show()
    }

    private fun storeImageInternal(context: Context, imageUri: Uri): Uri? {
        resetProfilePicture(this) // Reset before storing the new one
        var outputStream: FileOutputStream? = null
        var inputStream: InputStream? = null
        try {
            val fileName = "permanent_selected_image.jpg"
            val internalFile = File(context.filesDir, fileName)

            inputStream = context.contentResolver.openInputStream(imageUri)
            outputStream = FileOutputStream(internalFile)

            inputStream?.copyTo(outputStream)

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
            apply()
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
            // Set a default image if no image is stored
            imageView.setImageResource(R.drawable.btn_5) // Replace with your default image drawable
        }
    }

    private fun resetProfilePicture(context: Context) {
        val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            remove("stored_image_uri")
            apply()
        }

        val fileName = "permanent_selected_image.jpg"
        val internalFile = File(context.filesDir, fileName)
        if (internalFile.exists()) {
            internalFile.delete()
        }

        // Set the ImageView to a default image
        imageView.setImageResource(R.drawable.btn_5) // Replace with your default image drawable
    }
}