package com.zestworks.mvvmpaging.model
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class EmployeeData(
		@SerializedName("page") var page: Int,
		@SerializedName("per_page") var perPage: Int,
		@SerializedName("total") var total: Int,
		@SerializedName("total_pages") var totalPages: Int,
		@SerializedName("data") var data: List<Data>
)

@Entity
data class Data(
		@PrimaryKey @SerializedName("id") var id: Int,
		@SerializedName("first_name") var firstName: String,
		@SerializedName("last_name") var lastName: String,
		@SerializedName("avatar") var avatar: String
)