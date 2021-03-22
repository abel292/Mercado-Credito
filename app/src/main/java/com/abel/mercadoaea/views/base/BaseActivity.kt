package com.abel.mercadoaea.views.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.abel.mercadoaea.R
import com.abel.mercadoaea.views.resultList.ResultActivity
import com.abel.mercadoaea.views.viewerItem.ViewerItemActivity

abstract class BaseActivity : AppCompatActivity() {


    protected fun show(idItem: String) {
        val intent = Intent(this, ViewerItemActivity::class.java)
        intent.putExtra(KEY_ID_ITEM, idItem)
        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }

    protected fun showSearchedResult(query: String) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(KEY_QUERY_EXTRA, query)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    companion object {
        const val KEY_QUERY_EXTRA = "argQuery"
        const val KEY_ID_ITEM = "argIdProduct"
    }
}