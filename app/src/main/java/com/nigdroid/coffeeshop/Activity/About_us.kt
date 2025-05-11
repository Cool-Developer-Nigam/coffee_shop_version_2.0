package com.nigdroid.coffeeshop.Activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.nigdroid.coffeeshop.databinding.ActivityAboutUsBinding

class About_us : AppCompatActivity() {

    private lateinit var binding: ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Back button functionality
        binding.backBtn.setOnClickListener {
            finish()
        }

        // Social media buttons
        binding.insta.setOnClickListener {
            openLinkInBrowser("https://www.instagram.com/mr_nigam_786/?hl=en")
        }

        binding.link.setOnClickListener {
            openLinkInBrowser("https://www.linkedin.com/in/nigam-prasad-sahoo-b0768034a/")
        }

        binding.facebook.setOnClickListener {
            openLinkInBrowser("https://www.facebook.com/nigamprasad.sahoo.1")
        }
    }

    // Function to open a link in the default browser (prefer Chrome if installed)
    private fun openLinkInBrowser(url: String) {
        val uri = Uri.parse(url)

        // Attempt to open in Chrome first
        val chromeIntent = Intent(Intent.ACTION_VIEW, uri).apply {
            addCategory(Intent.CATEGORY_BROWSABLE)
            setPackage("com.android.chrome")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        try {
            if (chromeIntent.resolveActivity(packageManager) != null) {
                startActivity(chromeIntent)
                Log.d("AboutUsActivity", "Opened in Chrome: $url")
            } else {
                // Fallback to any browser
                val fallbackIntent = Intent(Intent.ACTION_VIEW, uri).apply {
                    addCategory(Intent.CATEGORY_BROWSABLE)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                startActivity(fallbackIntent)
                Log.d("AboutUsActivity", "Opened in default browser: $url")
            }
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "No web browser found to open the link.", Toast.LENGTH_LONG).show()
            Log.e("AboutUsActivity", "Error opening link: $url", e)
        }
    }
}
