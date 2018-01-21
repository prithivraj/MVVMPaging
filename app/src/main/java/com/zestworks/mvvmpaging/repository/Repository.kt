package com.zestworks.mvvmpaging.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.zestworks.mvvmpaging.model.Data


interface Repository {
    fun fetchEmployees(pageNumber: Int) : LiveData<Status>
    fun getEmployeePagedList(boundaryCallback: PagedList.BoundaryCallback<Data>?, page_size: Int): LiveData<PagedList<Data>>
    fun clearData()
}

enum class Status {
    FAILURE,
    SUCCESS
}
