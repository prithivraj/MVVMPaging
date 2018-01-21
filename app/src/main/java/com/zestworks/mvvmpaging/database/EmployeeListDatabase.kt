package com.zestworks.mvvmpaging.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.zestworks.mvvmpaging.model.Data

@Database(entities = [(Data::class)], version = 1)
abstract class EmployeeListDatabase : RoomDatabase() {
    abstract fun employeeListDAO() : EmployeeListDAO
}