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

class ColorSelectionAdapter(
    private var context: Context, private var itemSizeInfoList: List<String>?
) : RecyclerView.Adapter<ColorSelectionAdapter.MyViewHolder>() {

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

        holder.tvColor.text = itemSizeInfoList?.get(position)
        holder.tvPrice.visibility = View.GONE
        holder.tvStockDetails.visibility = View.GONE
        holder.rlSizeView.setOnClickListener {
            selectedPosition = position
            onClickListener?.onButtonClick(position)
            notifyDataSetChanged()
        }

        if (selectedPosition == position) {
            holder.rlSizeView.setBackgroundResource(R.drawable.bg_selected_size)
            Utils.textViewGradient(holder.tvPrice, activity as MainActivity)
        } else {
            holder.rlSizeView.setBackgroundResource(R.drawable.bg_default_size)
            Utils.textViewWhite(holder.tvPrice, activity as MainActivity)
        }
    }

    override fun getItemCount(): Int {
        return itemSizeInfoList?.size!!
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvPrice = view.findViewById<TextView>(R.id.tvPrice)
        var tvColor = view.findViewById<TextView>(R.id.tvSize)
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
