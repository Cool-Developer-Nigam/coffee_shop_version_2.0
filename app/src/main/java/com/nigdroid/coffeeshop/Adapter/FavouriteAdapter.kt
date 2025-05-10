package com.nigdroid.coffeeshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nigdroid.coffeeshop.Domain.ItemsModel
import com.nigdroid.coffeeshop.Helper.ManagementFavourite // Import ManagementFavourite
import com.nigdroid.coffeeshop.databinding.ViewholderFavouriteBinding

// Define an interface for the listener
interface ChangeNumberItemsListener {
    fun onChanged()
}

class FavouriteAdapter(
    private val favouriteItems: ArrayList<ItemsModel>,
    private val context: Context,
    private val changeNumberItemsListener: ChangeNumberItemsListener? = null // Make listener nullable
) : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {

    // Instantiate ManagementFavourite
    private val managementFavourite = ManagementFavourite(context)

    class ViewHolder(val binding: ViewholderFavouriteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = favouriteItems[position]

        holder.binding.apply {
            titleTxt.text = item.title
            priceTxt.text = "$${item.price}" // Format the price

            // Load the item image (assuming picUrl is a list and you want the first one)
            if (item.picUrl.isNotEmpty()) {
                Glide.with(holder.itemView.context)
                    .load(item.picUrl[0])
                    .into(pic)
            } else {
                // Set a placeholder image or hide the ImageView if no image is available
                // pic.setImageResource(R.drawable.placeholder_image)
            }


            // Handle removing from favourites when deleteBtn is clicked
            // Make sure the ID in your viewholder_favourite.xml is 'deleteBtn'
            deleteBtn.setOnClickListener {
                // Call the removeItem method from ManagementFavourite
                managementFavourite.removeItem(item)

                // Remove the item from the adapter's data source
                favouriteItems.removeAt(position)

                // Notify the adapter about the item removal
                notifyItemRemoved(position)

                // Notify that the range of items has changed, which is important for proper list updates
                notifyItemRangeChanged(position, favouriteItems.size)

                // Notify the listener (FavouriteActivity) that the list has changed
                changeNumberItemsListener?.onChanged()
            }
        }
    }

    override fun getItemCount(): Int = favouriteItems.size
}