package com.ubaya.studentapp.view

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

@BindingAdapter("android:imageUrl")
fun loadPhotoUrl(imageView: ImageView, url: String) {
    val picasso = Picasso.Builder(imageView.context)
    picasso.listener { picasso, uri, exception ->
        exception.printStackTrace()
    }
    picasso.build().load(url).into(imageView)
}

//fun loadPhotoUrlDetail(imageView: ImageView, url: String) {
//    Picasso.get().load(url).into(imageView)
//}