package com.ubaya.studentapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.studentapp.model.Student
import com.ubaya.studentapp.view.StudentDetailFragmentArgs

class DetailViewModel(application: Application, savedStateHandle: SavedStateHandle):AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()
    val TAG = "volleyTag"

//    ini belom keluar keknya
    var id = StudentDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).studentId

    private var queue: RequestQueue? = null

    fun fetch() {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php?id=${id}"

        val stringRequest = StringRequest(
            Request.Method.GET, url, {
                val sType = object : TypeToken<Student>() {}.type
                val result = Gson().fromJson<Student>(it, sType)
                val student1 = result as Student

                studentLD.value = student1

                Log.d("show_volley", it) //kalau sukses, it akan berisi JSON string (data dari student.php)

            },
            {
                Log.e("show_volley", it.toString())
            }
        )

        stringRequest.tag = TAG
//        krn queue bisa null, dikasih tanda tanya
        queue?.add(stringRequest)
    }
}