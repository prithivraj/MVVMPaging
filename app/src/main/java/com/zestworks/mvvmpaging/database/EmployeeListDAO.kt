package com.zestworks.mvvmpaging.database

import android.arch.paging.DataSource
import android.arch.persistence.room.*
import com.zestworks.mvvmpaging.model.Data

@Dao
interface EmployeeListDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllEmployees(employeeDataList: List<Data>?)

    @Query("DELETE FROM data")
    fun clearEmployees()

    @Query("SELECT * FROM data")
    fun getEmployees() : DataSource.Factory<Int, Data>
}