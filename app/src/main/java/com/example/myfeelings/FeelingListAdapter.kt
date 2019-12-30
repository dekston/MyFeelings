package com.example.feelings

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FeelingListAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<FeelingListAdapter.FeelingViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var feelings = emptyList<Feeling>() // Cached copy of words

    inner class FeelingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val feelingNameTextView: TextView = itemView.findViewById(R.id.textViewName)
        val feelingContactTextView : TextView = itemView.findViewById(R.id.textViewContact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeelingViewHolder {
        val itemView = inflater.inflate(R.layout.feeling_record, parent, false)
        return FeelingViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FeelingViewHolder, position: Int) {
        val current = feelings[position]
        holder.feelingNameTextView.text = current.name
        holder.feelingContactTextView.text = current.contact
    }

    internal fun setFeelings(feelings: List<Feeling>) {
        this.feelings = feelings
        notifyDataSetChanged()
    }

    override fun getItemCount() = feelings.size
}