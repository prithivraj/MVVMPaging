package com.zestworks.mvvmpaging.di

import android.arch.persistence.room.Room
import android.content.Context
import com.squareup.picasso.Picasso
import com.zestworks.mvvmpaging.database.EmployeeListDatabase
import com.zestworks.mvvmpaging.repository.EmployeeDataRepository
import com.zestworks.mvvmpaging.repository.Repository
import com.zestworks.mvvmpaging.services.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(private val context: Context) {

    @Provides
    @PerApp
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://reqres.in/")
                .build()
    }

    @Provides
    @PerApp
    fun provideMockApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @PerApp
    fun provideRepository(apiService: ApiService, employeeListDatabase: EmployeeListDatabase): Repository {
        return EmployeeDataRepository(apiService, employeeListDatabase)
    }

    @Provides
    @PerApp
    fun providePicasso(): Picasso {
        return Picasso.with(context)
    }

    @Provides
    @PerApp
    fun provideDatabase(): EmployeeListDatabase{
        //return Room.inMemoryDatabaseBuilder(context, EmployeeListDatabase::class.java).build()
        return Room.databaseBuilder(context, EmployeeListDatabase::class.java, "EmployeeList.db").build()
    }
}