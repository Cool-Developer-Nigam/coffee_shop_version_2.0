package com.nigdroid.coffeeshop.Helper

import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nigdroid.coffeeshop.Domain.ItemsModel // Assuming you have an ItemsModel

class ManagementFavourite(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("favourite_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val favouriteListKey = "favouriteItems"

    fun insertItem(item: ItemsModel) {
        val listFavourite = getListFavourite()
        val existItem = listFavourite.find { it.title == item.title } // Check if item already exists

        if (existItem == null) {
            listFavourite.add(item)
            saveListFavourite(listFavourite)
            Toast.makeText(context, "Added to Favourite", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Already in Favourite", Toast.LENGTH_SHORT).show()
        }
    }

    fun removeItem(item: ItemsModel) {
        val listFavourite = getListFavourite()
        listFavourite.remove(item) // Note: This might require implementing equals() in ItemsModel
        saveListFavourite(listFavourite)
        Toast.makeText(context, "Removed from Favourite", Toast.LENGTH_SHORT).show()
    }

    fun getListFavourite(): ArrayList<ItemsModel> {
        val json = sharedPreferences.getString(favouriteListKey, null)
        return if (json != null) {
            val type = object : TypeToken<ArrayList<ItemsModel>>() {}.type
            gson.fromJson(json, type)
        } else {
            ArrayList()
        }
    }

    private fun saveListFavourite(listFavourite: ArrayList<ItemsModel>) {
        val json = gson.toJson(listFavourite)
        sharedPreferences.edit().putString(favouriteListKey, json).apply()
    }
}