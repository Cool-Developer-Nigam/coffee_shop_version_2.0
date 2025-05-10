package com.nigdroid.coffeeshop.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nigdroid.coffeeshop.Activity.DetailActivity
import com.nigdroid.coffeeshop.Domain.ItemsModel
import com.nigdroid.coffeeshop.databinding.ViewholderItemPicLeftBinding
import com.nigdroid.coffeeshop.databinding.ViewholderItemPicRightBinding

class ItemsListCategoryAdapter(val items:MutableList<ItemsModel>)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

         companion object{
             const val TYPE_ITEM1=0
             const val TYPE_ITEM2=1

         }
    lateinit var context:Context
    override fun getItemViewType(position: Int): Int {

        return if(position%2==0){
            TYPE_ITEM1
        }else{
            TYPE_ITEM2
        }
    }

    class viewholderITEM2(val binding:ViewholderItemPicLeftBinding):RecyclerView.ViewHolder(binding.root)
    class viewholderITEM1(val binding:ViewholderItemPicRightBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
       context=parent.context

        return when(viewType){

            TYPE_ITEM2->{
                val binding=ViewholderItemPicLeftBinding.inflate(LayoutInflater.from(context),parent,false)
                viewholderITEM2(binding)
            }
            TYPE_ITEM1->{
                val binding=ViewholderItemPicRightBinding.inflate(LayoutInflater.from(context),parent,false)
                viewholderITEM1(binding)
            }

            else -> throw IllegalArgumentException("Invalidview type")

        }
    }
    override fun getItemCount(): Int =items.size


    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
      val item=items[position]
        fun bindCommonData(
            titleTxt:String,
            priceTxt: String,
            rating: Float,
            picUrl: String
            ) {
            when(holder){
                is viewholderITEM1-> {
                    holder.binding.titleTxt.text = titleTxt
                    holder.binding.priceTxt.text = priceTxt
                    holder.binding.ratingBar.rating = rating


                    Glide.with(context)
                        .load(picUrl)
                        .into(holder.binding.picMain)


                    holder.itemView.setOnClickListener {
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra("object", item)
                        context.startActivity(intent)

                    }
                }
                    is viewholderITEM2->{
                    holder.binding.titleTxt.text=titleTxt
                    holder.binding.priceTxt.text=priceTxt
                    holder.binding.ratingBar.rating=rating


                    Glide.with(context)
                        .load(picUrl)
                        .into(holder.binding.picMain)


                    holder.itemView.setOnClickListener {
                        val intent=Intent(context,DetailActivity::class.java)
                        intent.putExtra("object",item)
                        context.startActivity(intent)

                    }

                }
            }
        }
bindCommonData(item.title,"${item.price} USD",item.rating.toFloat(),item.picUrl[0])

    }



}