package com.nigdroid.coffeeshop.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nigdroid.coffeeshop.Domain.BannerModel
import com.nigdroid.coffeeshop.Domain.CategoryModel
import com.nigdroid.coffeeshop.Domain.ItemsModel
import com.nigdroid.coffeeshop.Repository.MainRepository

class MainViewModel:ViewModel() {

    private val repository=MainRepository()

    fun loadBanner():LiveData<MutableList<BannerModel>>{

        return repository.loadBanner()

    }

    fun loadCatagory():LiveData<MutableList<CategoryModel>>{

        return repository.loadCatagory()

    }

    fun loadPopular():LiveData<MutableList<ItemsModel>>{
        return repository.loadPopular()

    }
    fun loadItems(categoryId:String):LiveData<MutableList<ItemsModel>>{

        return repository.loadItemCategory(categoryId)

    }

}