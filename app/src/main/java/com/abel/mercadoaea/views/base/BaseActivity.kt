package com.abel.mercadoaea.views.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.abel.mercadoaea.views.resultList.ResultActivity
import com.abel.mercadoaea.views.viewerItem.ViewerItemActivity

abstract class BaseActivity : AppCompatActivity() {


    protected fun show(idItem: String) {
        val intent = Intent(this, ViewerItemActivity::class.java)
        intent.putExtra(KEY_ID_ITEM, idItem)
        startActivity(intent)
    }

    protected fun showSearchedResult(query: String) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(KEY_QUERY_EXTRA, query)
        startActivity(intent)
    }

    companion object {
        const val KEY_QUERY_EXTRA = "argQuery"
        const val KEY_ID_ITEM = "argIdProduct"
    }
}