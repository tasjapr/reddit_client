package com.example.redditclient.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.redditclient.databinding.ItemEntryBinding

class RVAdapter(
    private var items: ArrayList<Entry>,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    lateinit var binding : ItemEntryBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemEntryBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun replaceData(arrayList: ArrayList<Entry>) {
        items = arrayList
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: ItemEntryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entry: Entry, listener: OnItemClickListener?) {
            binding.entry = entry
            if (listener != null) {
                binding.root.setOnClickListener { listener.onItemClick(layoutPosition) }
            }

            binding.executePendingBindings()
        }
    }

}