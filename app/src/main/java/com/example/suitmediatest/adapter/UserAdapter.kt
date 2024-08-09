package com.example.suitmediatest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.suitmediatest.R
import com.example.suitmediatest.data.response.DataItem
import com.example.suitmediatest.databinding.ItemUserBinding

class UserAdapter : PagingDataAdapter<DataItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    private var onItemClickListener: ((DataItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    inner class MyViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem?) {
            user?.let {
                binding.tvUsername.text = "${it.firstName} ${it.lastName}"
                binding.tvEmail.text = it.email
                Glide.with(itemView.context)
                    .load(it.avatar)
                    .apply(RequestOptions.placeholderOf(R.drawable.no_profile))
                    .into(binding.imgUser)
                itemView.setOnClickListener {
                    onItemClickListener?.invoke(user)
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (DataItem) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}

