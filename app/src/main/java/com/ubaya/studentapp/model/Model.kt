package com.ubaya.studentapp.model

import com.google.gson.annotations.SerializedName

data class Student(
    var id:String?,
    @SerializedName("student_name")
    var name:String?,
    @SerializedName("birth_of_date")
    var dob:String?,
    var phone:String?,
    @SerializedName("photo_url")
    var photoUrl:String?
)

data class Plane (
    var id:String?,
    var manufacturer:String?,
    var model:String?,
    var capacity:Int?,
    @SerializedName("max_speed")
    var maxSpeed:Int?,
    var colors:List<String>?,
    var dimensions:PlaneDimension?,
    @SerializedName("images")
    var image:String?
)

data class PlaneDimension(
    var length:Double?,
    var wingspan:Double?,
    var height:Double?
)