package com.zestworks.mvvmpaging.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zestworks.mvvmpaging.R
import com.zestworks.mvvmpaging.di.AppComponentProvider
import com.zestworks.mvvmpaging.model.Data
import com.zestworks.mvvmpaging.viewmodels.EmployeeListViewModel
import kotlinx.android.synthetic.main.list_fragment.*


class MainFragment: Fragment() {

    private lateinit var employeeListViewModel: EmployeeListViewModel
    private lateinit var employeeListObserver: Observer<PagedList<Data>>
    private lateinit var employeeListAdapter : EmployeeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        employeeListViewModel = ViewModelProviders.of(this.activity!!).get(EmployeeListViewModel::class.java)
        employeeListAdapter = EmployeeListAdapter()
        AppComponentProvider.instance.appComponentInstance.inject(employeeListViewModel)
        AppComponentProvider.instance.appComponentInstance.inject(employeeListAdapter)
    }

    private fun bindToObservables() {
        employeeListObserver = Observer { pagedList ->
            if ( pagedList != null ) {
               employeeListAdapter.setList(pagedList)
                employeeListAdapter.notifyDataSetChanged()
            }
        }
        employeeListViewModel.getEmployeesLiveData().observe(this, employeeListObserver)
        employeeListViewModel.uiPopups.observe(this, Observer { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val linearLayoutManager = LinearLayoutManager(view.context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        val recyclerView = view.findViewById<RecyclerView>(R.id.emplist)
        recyclerView?.adapter = employeeListAdapter
        recyclerView?.layoutManager = linearLayoutManager
        bindToObservables()
        employeeListViewModel.fetchEmployeesList()

        list_fragment_clear.setOnClickListener {
            employeeListViewModel.clearData()
        }

        list_fragment_fetch.setOnClickListener{
            employeeListViewModel.fetchEmployeesList()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.list_fragment, container, false)
}