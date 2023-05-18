package com.example.youtube.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String){
    Glide.with(this).load(url).into(this)
}
fun toast(context: Context,text:String){
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}