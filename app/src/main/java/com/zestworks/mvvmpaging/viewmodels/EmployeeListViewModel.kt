package com.zestworks.mvvmpaging.viewmodels

import android.arch.lifecycle.*
import android.arch.paging.PagedList
import android.util.Log
import com.zestworks.mvvmpaging.model.Data
import com.zestworks.mvvmpaging.repository.Repository
import com.zestworks.mvvmpaging.repository.Status
import javax.inject.Inject

class EmployeeListViewModel : ViewModel() {
    internal val uiPopups: MutableLiveData<String> = MutableLiveData()
    private var currentPageNumber = 1
    private val databasePageSize = 5
    private lateinit var lastRequestStatus: LiveData<Status>

    @Inject
    lateinit var repository: Repository

    fun fetchEmployeesList() {
        Log.d("Viewmodel", "fetchEmployeesList called, currentPageNumber : " + currentPageNumber)
        lastRequestStatus = repository.fetchEmployees(currentPageNumber)
        //TODO Return this directly instead
        lastRequestStatus.observeForever {
            when (it) {
                Status.FAILURE -> uiPopups.postValue("No internet connection")
                Status.SUCCESS -> {}
                null -> uiPopups.postValue("Something is terribly wrong.")
            }
        }
    }

    fun getEmployeesLiveData(): LiveData<PagedList<Data>> {
        return repository.getEmployeePagedList(object : PagedList.BoundaryCallback<Data>() {
            override fun onItemAtEndLoaded(itemAtEnd: Data) {
                super.onItemAtEndLoaded(itemAtEnd)
                Log.d("Viewmodel", "onItemAtEndLoaded called")
                currentPageNumber += 1
                fetchEmployeesList()
            }
        }, databasePageSize)
    }

    fun clearData() {
        currentPageNumber = 1
        repository.clearData()
    }
}