package com.example.project1762.Helper

import android.content.Context
import android.widget.Toast
import com.nigdroid.coffeeshop.Domain.ItemsModel
import com.nigdroid.coffeeshop.Helper.TinyDB
import com.uilover.project195.Helper.ChangeNumberItemsListener


class ManagmentCart(val context: Context) {

    private val tinyDB = TinyDB(context)

    fun insertItems(item: ItemsModel) {
        var listItem = getListCart()
        val existAlready = listItem.any { it.title == item.title }
        val index = listItem.indexOfFirst { it.title == item.title }

        if (existAlready) {
            listItem[index].numberInCart = item.numberInCart
            Toast.makeText(context, "Already in your Cart", Toast.LENGTH_SHORT).show()
        } else {
            listItem.add(item)
            Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show()
        }
        tinyDB.putListObject("CartList", listItem)

    }

    fun getListCart(): ArrayList<ItemsModel> {
        return tinyDB.getListObject("CartList") ?: arrayListOf()
    }

    fun minusItem(listItems: ArrayList<ItemsModel>, position: Int, listener: ChangeNumberItemsListener) {
        if (listItems[position].numberInCart == 1) {
            listItems.removeAt(position)
        } else {
            listItems[position].numberInCart--
        }
        tinyDB.putListObject("CartList", listItems)
        listener.onChanged()
    }
    fun romveItem(listItems: ArrayList<ItemsModel>, position: Int, listener: ChangeNumberItemsListener) {

        listItems.removeAt(position)

        tinyDB.putListObject("CartList", listItems)
        listener.onChanged()
    }

    fun plusItem(listItems: ArrayList<ItemsModel>, position: Int, listener: ChangeNumberItemsListener) {
        listItems[position].numberInCart++
        tinyDB.putListObject("CartList", listItems)
        listener.onChanged()
    }

    fun getTotalFee(): Double {
        val listItem = getListCart()
        var fee = 0.0
        for (item in listItem) {
            fee += item.price * item.numberInCart
        }
        return fee
    }
}