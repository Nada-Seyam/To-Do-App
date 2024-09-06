package com.example.dem

import androidx.recyclerview.widget.DiffUtil

class DiffUtil (
    val oldList: List<User>,
    val newList:List<User>
):DiffUtil.Callback() {


    override fun getOldListSize()=oldList.size

    override fun getNewListSize()=newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]==newList[newItemPosition]

    }


}