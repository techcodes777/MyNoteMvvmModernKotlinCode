package com.eclatsol.mynotemvvmmodernkotlincode

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eclatsol.mynotemvvmmodernkotlincode.databinding.EachRvBinding

class RVAdapter : ListAdapter<Note, RVAdapter.ViewHolder>(NoteDiffCallback()) {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: EachRvBinding

        init {
            binding = EachRvBinding.bind(itemView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.each_rv, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = getNote(position)
        holder.binding.tittleRv.text = note.title
        holder.binding.dispRv.text = note.description
    }

     fun getNote(position: Int): Note {
        return getItem(position)
    }


}

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.title == newItem.title && oldItem.description == newItem.description
    }

}