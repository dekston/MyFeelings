package com.example.myfeelings

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class FeelingListAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<FeelingListAdapter.FeelingViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var feelingList = emptyList<Feeling>() // Cached copy of words

    inner class FeelingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewFeeling:ImageView= itemView.findViewById(R.id.imageViewHappy)
        val textViewRemark : TextView = itemView.findViewById(R.id.textViewRemark)
        val textViewDate : TextView = itemView.findViewById(R.id.textViewDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeelingViewHolder {
        val itemView = inflater.inflate(R.layout.feeling_record, parent, false)
        return FeelingViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FeelingViewHolder, position: Int) {
        val feeling=feelingList.get(position)
        if(feeling.mood==1){
            holder.imageViewFeeling.setImageResource(R.drawable.ic_sad)

        }
        else if(feeling.mood==2){
            holder.imageViewFeeling.setImageResource(R.drawable.ic_neutral)

        }
        else if(feeling.mood==3){
            holder.imageViewFeeling.setImageResource(R.drawable.ic_happy)

        }
        holder.textViewRemark.text=feeling.remarks
        holder.textViewDate.text=SimpleDateFormat("yyyy.MM.dd").format(feeling.created_at)
    }

    internal fun setFeelings(feelings: List<Feeling>) {
        this.feelingList = feelings
        notifyDataSetChanged()
    }

    override fun getItemCount() = feelingList.size
}