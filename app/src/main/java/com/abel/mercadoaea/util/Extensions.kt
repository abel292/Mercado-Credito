package com.abel.mercadoaea.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import com.abel.mercadoaea.data.database.ItemEntity
import com.abel.mercadoaea.data.model.item.ResponseItem

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.isValidGlideContext() = this !is Activity || (!this.isDestroyed && !this.isFinishing)

const val SEARCHABLE_DESTINATION = "AppSearchData"

@SuppressLint("RestrictedApi")
fun SearchView.configureSearchableInfo() {
    val activity = context as Activity
    val appContext = activity.applicationContext
    val searchManager = appContext.getSystemService(Context.SEARCH_SERVICE) as SearchManager
    this.setSearchableInfo(searchManager.getSearchableInfo(activity.componentName))
}

fun ResponseItem.toItemEntity(): ItemEntity {
    return ItemEntity(id = id, title = title, urlImage = thumbnail, price = price.toString())
}

fun View.getParentActivity(): AppCompatActivity? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

@SuppressLint("ClickableViewAccessibility")
fun View.setOnClick(clickEvent: () -> Unit) {
    this.setOnTouchListener { _, event ->
        try {
            if (event.action == MotionEvent.ACTION_UP) {
                clickEvent.invoke()
            }
            false
        } catch (e: Exception) {
            Log.e(this.javaClass.name, "error en Onclick: $e")
            false
        }
    }
}