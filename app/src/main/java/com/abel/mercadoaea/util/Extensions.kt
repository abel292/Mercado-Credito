package com.abel.mercadoaea.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf

fun Context.toast(message :String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

const val SEARCHABLE_DESTINATION = "AppSearchData"
@SuppressLint("RestrictedApi")
fun SearchView.configureSearchableInfo() {
    val activity = context as Activity
    val appContext = activity.applicationContext
    val searchManager = appContext.getSystemService(Context.SEARCH_SERVICE) as SearchManager
    this.setSearchableInfo(searchManager.getSearchableInfo(activity.componentName))
}