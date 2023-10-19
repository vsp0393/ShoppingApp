package com.vsple.purchaseapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vsple.purchaseapp.Activity.MainActivity
import com.vsple.purchaseapp.Models.Size
import com.vsple.purchaseapp.R
import com.vsple.purchaseapp.Utils

class ItemSizeAdapter(
    private var context: Context, private var itemSizeInfoList: List<Size>?
) : RecyclerView.Adapter<ItemSizeAdapter.MyViewHolder>() {

    var selectedPosition = -1
    val activity = context as? MainActivity
    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_size_details, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvSize.text = itemSizeInfoList?.get(position)?.sizeType.toString()
        holder.tvPrice.text = "₹ "+ itemSizeInfoList?.get(position)?.prize.toString()

        if (itemSizeInfoList?.get(position)?.availability == true) {
            holder.rlSizeView.setOnClickListener {
                selectedPosition = position
                onClickListener?.onButtonClick(position)
                notifyDataSetChanged()
            }
        }

        if (selectedPosition == position) {
            holder.rlSizeView.setBackgroundResource(R.drawable.bg_selected_size)
            Utils.textViewGradient(holder.tvPrice, activity as MainActivity)
        } else {
            if (itemSizeInfoList?.get(position)?.availability == true) {
                holder.rlSizeView.setBackgroundResource(R.drawable.bg_default_size)
                holder.tvStockDetails.visibility = View.GONE
                Utils.textViewWhite(holder.tvPrice, activity as MainActivity)
                holder.tvSize.setTextColor(context.getColor(R.color.white))
            } else {
                holder.rlSizeView.setBackgroundResource(R.drawable.bg_notavailble_size)
                holder.tvStockDetails.visibility = View.VISIBLE
                Utils.textViewGray(holder.tvPrice, activity as MainActivity)
                holder.tvSize.setTextColor(context.getColor(R.color.color_text_light_gray))

            }
        }
    }

    override fun getItemCount(): Int {
        return itemSizeInfoList?.size!!
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvPrice = view.findViewById<TextView>(R.id.tvPrice)
        var tvSize = view.findViewById<TextView>(R.id.tvSize)
        var tvStockDetails = view.findViewById<TextView>(R.id.tvStockDetails)
        var rlSizeView = view.findViewById<RelativeLayout>(R.id.rlSizeView)
    }

    interface OnClickListener {
        fun onButtonClick(position: Int)
    }

    fun setOnClickListener(OnClickListener: OnClickListener) {
        this.onClickListener = OnClickListener
    }
}
