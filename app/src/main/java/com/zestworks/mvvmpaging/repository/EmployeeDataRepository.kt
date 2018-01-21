package com.zestworks.mvvmpaging.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.zestworks.mvvmpaging.database.EmployeeListDatabase
import com.zestworks.mvvmpaging.model.Data
import com.zestworks.mvvmpaging.model.EmployeeData
import com.zestworks.mvvmpaging.services.ApiService
import kotlinx.coroutines.experimental.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EmployeeDataRepository(private val apiService: ApiService, private val employeeListDatabase: EmployeeListDatabase) : Repository {

    override fun getEmployeePagedList(boundaryCallback: PagedList.BoundaryCallback<Data>?, page_size: Int): LiveData<PagedList<Data>> {
        val employeesFromDB = employeeListDatabase.employeeListDAO().getEmployees()
        val livePagedListBuilder = LivePagedListBuilder(employeesFromDB, page_size)
        return livePagedListBuilder.setBoundaryCallback(boundaryCallback).build()
    }

    override fun fetchEmployees(pageNumber: Int): LiveData<Status> {
        val liveData = MutableLiveData<Status>()
        val allEmployees = apiService.getAllEmployees(pageNumber)
        allEmployees.enqueue(object : Callback<EmployeeData> {
            override fun onFailure(call: Call<EmployeeData>?, t: Throwable?) {
                liveData.postValue(Status.FAILURE)
            }

            override fun onResponse(call: Call<EmployeeData>?, response: Response<EmployeeData>?) {
                launch {
                    val employeeDataList = response?.body()?.data
                    if (employeeDataList != null) {
                        employeeListDatabase.employeeListDAO().addAllEmployees(employeeDataList)
                        liveData.postValue(Status.SUCCESS)
                    }
                }
            }
        })
        return liveData
    }

    override fun clearData() {
        launch {
            employeeListDatabase.employeeListDAO().clearEmployees()
        }
    }

}