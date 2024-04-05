package com.ubaya.studentapp.view

import android.graphics.drawable.Icon
import android.Manifest
import android.content.ClipDescription
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ubaya.studentapp.R
import com.ubaya.studentapp.databinding.ActivityMainBinding
import com.ubaya.studentapp.util.createNotificationChannel

class MainActivity : AppCompatActivity() {
    init {
        instance = this
    }

    companion object {
//        bisa null, karena tidak bisa new MainActivity
        private var instance:MainActivity ?= null

        fun showNotification(title: String, content: String, icon: Int) {
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"
//            com.ubaya.studentapp-StudentApp --> channel idnya

//            tanda seru untuk ngasih tau kotlin kalo gabakal null
            val builder = NotificationCompat.Builder(instance!!.applicationContext, channelId).apply {
                setSmallIcon(icon)
                setContentTitle(title)
                setContentText(content)
                setStyle(NotificationCompat.BigTextStyle())
                priority = NotificationCompat.PRIORITY_DEFAULT
                setAutoCancel(true)
            }

            val manager = NotificationManagerCompat.from(instance!!.applicationContext!!)

//            mulai oreo ke atas, perlu ngecek self permission (camera, notifikasi, dst)
//            jika tidak diijinkan
            if (ActivityCompat.checkSelfPermission(instance!!.applicationContext, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
//                ini untuk minta ijin ke user tentang permission (bisa lebih dari 1 permission sekaligus, tinggal ditambah di dalam arraynya)
//                parameter pertama activity (diri sendiri), parameter kedua permission yg mau diminta, parameter ketiga kode random
                ActivityCompat.requestPermissions(instance!!, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
                return
            }

//            ini kalo if yg di atas, dilewatin (diijinin oleh user)
            manager.notify(1001, builder.build())
        }
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false, getString(R.string.app_name), "App notification channel.")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("permission", "granted")
                    createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false, getString(R.string.app_name), "App notification channel.")
                } else {
                    Log.d("permission", "denied")
                }
                return
            }
        }
    }



}