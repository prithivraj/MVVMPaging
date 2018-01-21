package com.zestworks.mvvmpaging.ui

import android.support.v7.recyclerview.extensions.DiffCallback
import com.zestworks.mvvmpaging.model.Data


object EmpDataDiffCallback : DiffCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}