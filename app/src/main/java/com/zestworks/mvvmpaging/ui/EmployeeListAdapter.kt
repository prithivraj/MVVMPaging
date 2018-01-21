package com.zestworks.mvvmpaging.ui

import android.arch.paging.PagedListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.zestworks.mvvmpaging.R
import com.zestworks.mvvmpaging.model.Data
import javax.inject.Inject


class EmployeeListAdapter : PagedListAdapter<Data, EmpListViewHolder>(EmpDataDiffCallback) {

    @Inject
    lateinit var picasso: Picasso

    override fun onBindViewHolder(holder: EmpListViewHolder?, position: Int) {
        val item = getItem(position)
        if(holder != null && item !=null){
            holder.id.text = item.id.toString()
            holder.firstName.text = item.firstName
            holder.lastName.text = item.lastName
            picasso.load(item.avatar).into(holder.avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): EmpListViewHolder {
        val holder = LayoutInflater.from(parent?.context).inflate(R.layout.list_item, parent, false)
        return EmpListViewHolder(holder)
    }
}