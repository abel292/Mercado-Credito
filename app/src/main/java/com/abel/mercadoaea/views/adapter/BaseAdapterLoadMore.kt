package com.abel.mercadoaea.views.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abel.mercadoaea.data.api.ContsApi.Companion.MORE
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener
import com.abel.mercadoaea.util.listeners.OnLoadMoreListener


abstract class BaseAdapterLoadMore<Object> : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    var list: ArrayList<Object?> = ArrayList()
    protected lateinit var onClickListener: OnClickItemRecyclerListener<Object>
    private lateinit var onLoadMoreListener: OnLoadMoreListener
    private lateinit var recyclerView: RecyclerView
    private var lastVisibleItem = 0
    private var totalItemCount = 0
    private var loading = false
    val viewItem = 1
    val viewLoad = 0

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        setRecycler(recyclerView)
        configOnScrollListener()
    }

    private fun setRecycler(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

    fun addMoreItems(items: List<Object>) {
        stopModeLoading()
        items.forEach {
            list.add(it)
            notifyItemInserted(list.size)
        }
    }

    fun modeLoading() {
        list.add(null)
        notifyItemInserted(list.size - 1)
    }

    private fun stopModeLoading() {
        if (list.isNotEmpty()) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                list.removeIf { item -> item == null }
            } else {
                list.removeAt(list.size - 1)
                notifyItemRemoved(list.size)
            }
        }
        loading = false
    }

    private fun configOnScrollListener() {
        if (recyclerView.layoutManager is LinearLayoutManager) {
            val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    totalItemCount = linearLayoutManager!!.itemCount
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()

                    if (!loading && totalItemCount <= lastVisibleItem + MORE) {
                        onLoadMoreListener.onLoadMore(list.size)
                        loading = true
                    }
                }
            })
        }
    }

    @JvmName("setOnLoadMoreListener1")
    fun setOnLoadMoreListener(
        onLoadMoreListener: OnLoadMoreListener,
        onClickItemRecyclerListener: OnClickItemRecyclerListener<Object>
    ) {
        this.onLoadMoreListener = onLoadMoreListener
        this.onClickListener = onClickItemRecyclerListener
    }
}