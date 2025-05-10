package com.nigdroid.coffeeshop.Activity

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.nigdroid.coffeeshop.Adapter.FavouriteAdapter // Import your FavouriteAdapter
import com.nigdroid.coffeeshop.Adapter.ChangeNumberItemsListener // Import the listener interface
import com.nigdroid.coffeeshop.Helper.ManagementFavourite // Import ManagementFavourite
import com.nigdroid.coffeeshop.Domain.ItemsModel // Import ItemsModel if not already imported
import com.nigdroid.coffeeshop.databinding.ActivityFavouriteBinding // Import your FavouriteActivity binding

// Implement the ChangeNumberItemsListener interface
class FavouriteActivity : AppCompatActivity(), ChangeNumberItemsListener {

    private lateinit var binding: ActivityFavouriteBinding
    private lateinit var managementFavourite: ManagementFavourite // Instantiate ManagementFavourite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ManagementFavourite
        managementFavourite = ManagementFavourite(this)

        initList()
        initToolbar() // Assuming you have a method to set up the toolbar
        updateUI() // Call updateUI initially to set the correct view visibility





    }

    private fun initList() {
        // Get the favorite items from ManagementFavourite
        // The explicit <ItemsModel> is not strictly necessary in Kotlin, but can be kept for clarity
        val favouriteItems = managementFavourite.getListFavourite()

        // Set up the RecyclerView
        if (favouriteItems.isNotEmpty()) {
            binding.favouriteListView.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
            // Pass 'this' as the listener to the adapter
            // Ensure favouriteItems is treated as ArrayList<ItemsModel> if ManagementFavourite returns List
            val adapter = FavouriteAdapter(favouriteItems as ArrayList<ItemsModel>, this, this)
            binding.favouriteListView.adapter = adapter
        }
    }

    private fun initToolbar() {
        // Set up back button listener
        binding.backBtn.setOnClickListener { finish() }
        // Add any other toolbar setup here if needed
    }





    // Implement the onChanged method from the listener interface
    override fun onChanged() {
        // This method is called by the adapter when an item is removed
        updateUI() // Update the UI to reflect the changes (show/hide empty message)
    }

    // Helper method to update the UI based on the favorite list size
    private fun updateUI() {
        // Re-check the size of the favorite list and update the UI accordingly
        val favouriteItems = managementFavourite.getListFavourite()
        if (favouriteItems.isEmpty()) {
            binding.emptyTxt.visibility = View.VISIBLE
            binding.favouriteListView.visibility = View.GONE
        } else {
            binding.emptyTxt.visibility = View.GONE
            binding.favouriteListView.visibility = View.VISIBLE
        }
    }
}