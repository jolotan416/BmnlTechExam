package com.studytest.bmnltechexam.developer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.studytest.bmnltechexam.data.developer.Developer
import com.studytest.bmnltechexam.databinding.DeveloperItemBinding

class DevelopersAdapter(private val callback: DeveloperCallback) :
    RecyclerView.Adapter<DevelopersAdapter.ViewHolder>() {
    private val developers: AsyncListDiffer<Developer> =
        AsyncListDiffer(this, object : DiffUtil.ItemCallback<Developer>() {
            override fun areItemsTheSame(oldItem: Developer, newItem: Developer) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Developer, newItem: Developer) =
                oldItem == newItem
        })

    override fun getItemCount() = developers.currentList.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            DeveloperItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(developers.currentList[position])
    }

    fun setItems(items: List<Developer>) {
        developers.submitList(items)
    }

    inner class ViewHolder(private val binding: DeveloperItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(developer: Developer) {
            binding.apply {
                this.developer = developer
                root.setOnClickListener {
                    callback.onSelectDeveloper(developer)
                }
            }
        }
    }

    interface DeveloperCallback {
        fun onSelectDeveloper(developer: Developer)
    }
}