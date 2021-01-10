package com.shaadi.assignment.ui.inbox

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shaadi.assignment.R
import com.shaadi.assignment.data.local.db.entity.InboxUser
import com.shaadi.assignment.databinding.RowInboxUserBinding

class InboxAdapter(val clickListener: (InboxUser) -> Unit) : ListAdapter<InboxUser,
        InboxAdapter.ViewHolder>(NewsDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: RowInboxUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: InboxUser, clickListener: (InboxUser) -> Unit) {

            binding.user = item
            binding.executePendingBindings()

            itemView.setOnClickListener {
                clickListener.invoke(item)
            }

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val view: RowInboxUserBinding = DataBindingUtil.inflate(
                    layoutInflater,
                    R.layout.row_inbox_user,
                    parent,
                    false
                )

                return ViewHolder(view)
            }
        }
    }


}

class NewsDiffCallback : DiffUtil.ItemCallback<InboxUser>() {
    override fun areItemsTheSame(oldItem: InboxUser, newItem: InboxUser): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: InboxUser, newItem: InboxUser): Boolean {
        return oldItem == newItem
    }
}