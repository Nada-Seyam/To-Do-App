package com.example.dem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dem.databinding.ItemLayoutBinding

class UserAdapter(
    private val userList: MutableList<User>,
    private val onItemDelete: (User) -> Unit,
    private val onItemUpdate: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun getItemCount() = userList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindData(userList[position])
    }

    fun setUserList(list: List<User>) {
        userList.clear()
        userList.addAll(list)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(user: User) {
            binding.title.text = user.title
            binding.desc.text = user.details
            binding.delete.setOnClickListener {
                onItemDelete(user)
            }
            binding.update.setOnClickListener {
                onItemUpdate(user)
            }
        }
    }
}
