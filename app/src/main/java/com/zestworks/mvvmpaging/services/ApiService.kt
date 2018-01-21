package com.zestworks.mvvmpaging.services

import com.zestworks.mvvmpaging.model.EmployeeData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("/api/users")
    fun getAllEmployees(@Query("page") page : Int): Call<EmployeeData>
}