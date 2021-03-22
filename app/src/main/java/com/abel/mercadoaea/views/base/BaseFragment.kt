package com.abel.mercadoaea.views.base

import android.content.Intent
import androidx.fragment.app.Fragment
import com.abel.mercadoaea.views.base.BaseActivity.Companion.KEY_QUERY_EXTRA
import com.abel.mercadoaea.views.resultList.ResultActivity
import com.abel.mercadoaea.views.viewerItem.ViewerItemActivity

abstract class BaseFragment : Fragment() {

    fun showSearchedResult(query: String) {
        val intent = Intent(requireActivity(), ResultActivity::class.java)
        intent.putExtra(KEY_QUERY_EXTRA, query)
        startActivity(intent)
    }


    protected fun show(idItem: String) {
        val intent = Intent(requireActivity(), ViewerItemActivity::class.java)
        intent.putExtra(BaseActivity.KEY_ID_ITEM, idItem)
        startActivity(intent)
    }

}