package com.zestworks.mvvmpaging.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.zestworks.mvvmpaging.R


class EmpListViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val id : TextView
    val firstName : TextView
    val lastName : TextView
    val avatar : ImageView

    init {
        id = view.findViewById(R.id.emplist_id)
        firstName = view.findViewById(R.id.emplist_firstname)
        lastName = view.findViewById(R.id.emplist_lastname)
        avatar = view.findViewById(R.id.emplist_avatar)
    }
}