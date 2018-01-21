package com.zestworks.mvvmpaging.di

import com.zestworks.mvvmpaging.ui.EmployeeListAdapter
import com.zestworks.mvvmpaging.viewmodels.EmployeeListViewModel
import dagger.Component

@Component(modules = [(AppModule::class)])
@PerApp
interface AppComponent {
    fun inject(employeeListViewModel: EmployeeListViewModel)
    fun inject(employeeListViewModel: EmployeeListAdapter)
}