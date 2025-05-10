package com.nigdroid.coffeeshop.Adapter


import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nigdroid.coffeeshop.Activity.ItemsListActivity

import com.nigdroid.coffeeshop.Domain.CategoryModel
import com.nigdroid.coffeeshop.R
import com.nigdroid.coffeeshop.databinding.ViewholderCatagoryBinding

class CategoryAdapter(val items:MutableList<CategoryModel>)
    :RecyclerView.Adapter<CategoryAdapter.Viewholder>() {

        private lateinit var context: Context
        private var selectedPosition=-1
        private var lastSelectedPosition=-1

    inner class Viewholder(val binding:ViewholderCatagoryBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.Viewholder {


        context=parent.context
        val binding=ViewholderCatagoryBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)

    }

    override fun onBindViewHolder(holder: CategoryAdapter.Viewholder, position: Int) {

      val item=items[position]
        holder.binding.titleCat.text=item.title

        holder.binding.root.setOnClickListener {

            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            Handler(Looper.getMainLooper()).postDelayed({
                val intent=android.content.Intent(context, ItemsListActivity::class.java).apply {
                    putExtra("id",item.id.toString())
                    putExtra("title",item.title)

                }
                ContextCompat.startActivity(context,intent,null)
            },500)

        }
        if(selectedPosition==position){
            holder.binding.titleCat.setBackgroundResource(R.drawable.dark_brown_background)
            holder.binding.titleCat.setTextColor(context.resources.getColor(R.color.white))
        }
        else{
            holder.binding.titleCat.setBackgroundResource(R.drawable.white_background)
            holder.binding.titleCat.setTextColor(context.resources.getColor(R.color.DarkBrown))

        }

    }

    override fun getItemCount(): Int =items.size


}

