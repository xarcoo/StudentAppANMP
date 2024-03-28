package com.ubaya.studentapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.studentapp.model.Plane
import com.ubaya.studentapp.model.Student

class PlaneViewModel(application: Application):AndroidViewModel(application) {
    val planeLD = MutableLiveData<ArrayList<Plane>>()
    val TAG = "volleyTag"
    private var queue:RequestQueue? = null

    fun refresh() {
        Log.d("CEK MASUK", "masuk volley")
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/ANMP/planes.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url, {
                val sType = object : TypeToken<List<Plane>>() {}.type
                val result = Gson().fromJson<List<Plane>>(it, sType)
                planeLD.value = result as ArrayList<Plane>
                Log.d("show_volley", it)

            },
            {
                Log.e("show_volley", it.toString())
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}