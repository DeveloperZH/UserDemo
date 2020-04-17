package com.zh.commonuilibrary.controls.page_state

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.*
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.zh.commonuilibrary.R

/**
 * @describe: 控制页面不同的状态  加载/空白/错误
 * @author: Z H
 * @date: 2020/4/16 15:40
 * @pkgName: com.zh.commonuilibrary.pagestate
 */
@Suppress("unused")
@SuppressLint("InflateParams")
class PageStateHolder private constructor(context: Context, vararg viewList: View) {
    private val loadView: View = LayoutInflater.from(context).inflate(R.layout.layout_load_view, null)
    private val emptyView: View = LayoutInflater.from(context).inflate(R.layout.layout_empty_view, null)
    private val errorView: View = LayoutInflater.from(context).inflate(R.layout.layout_error_view, null)
    private val rootView: FrameLayout = FrameLayout(context)
    private var curState: LoadState = LoadState.SUCCESS

    constructor(activity: Activity, vararg viewList: View) : this(activity as Context, *viewList) {
        activity.addContentView(rootView, ViewGroup.LayoutParams(-1, -1))
    }

    constructor(viewGroup: ViewGroup, vararg viewList: View) : this(viewGroup.context, *viewList) {
        when (viewGroup) {
            is LinearLayout -> viewGroup.addView(rootView,0,ViewGroup.LayoutParams(-1,-1))
            is ConstraintLayout -> {
                viewGroup.addView(rootView, ViewGroup.LayoutParams(0, 0))
                val set = ConstraintSet()
                set.clone(viewGroup)
                set.connect(rootView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
                set.connect(rootView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
                set.connect(rootView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
                set.connect(rootView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
                set.applyTo(viewGroup)
            }
            else -> viewGroup.addView(rootView, ViewGroup.LayoutParams(-1, -1))
        }
    }

    init {
        rootView.addView(loadView)
        rootView.addView(emptyView)
        rootView.addView(errorView)
        loadView.visibility = View.GONE
        emptyView.visibility = View.GONE
        errorView.visibility = View.GONE
        rootView.visibility = View.GONE
        rootView.id = R.id.fl_fragment_container
        if (viewList.isNotEmpty()) {
            rootView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val viewHeight = viewList.sumBy { it.measuredHeight }
                    rootView.setPadding(0, viewHeight, 0, 0)
                    rootView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })
        }
    }

    fun setEmptyViewImg(drawable: Drawable): PageStateHolder {
        emptyView.findViewById<ImageView>(R.id.iv_empty).setImageDrawable(drawable)
        return this
    }

    fun setEmptyViewImg(bitmap: Bitmap): PageStateHolder {
        emptyView.findViewById<ImageView>(R.id.iv_empty).setImageBitmap(bitmap)
        return this
    }

    fun setEmptyViewImg(@DrawableRes resId: Int): PageStateHolder {
        emptyView.findViewById<ImageView>(R.id.iv_empty).setImageResource(resId)
        return this
    }

    fun setEmptyViewText(title: CharSequence): PageStateHolder {
        emptyView.findViewById<TextView>(R.id.tv_empty_desc).text = title
        return this
    }

    fun setEmptyButton(text: CharSequence, listener: View.OnClickListener): PageStateHolder {
        val emptyButton = emptyView.findViewById<TextView>(R.id.tv_empty_btn)
        emptyButton.visibility = if (TextUtils.isEmpty(text)) View.GONE else View.VISIBLE
        emptyButton.text = text
        emptyButton.setOnClickListener(listener)
        return this
    }

    fun setEmptyViewText(@StringRes titleResId: Int): PageStateHolder {
        emptyView.findViewById<TextView>(R.id.tv_empty_desc).setText(titleResId)
        return this
    }

    fun setErrorViewImg(drawable: Drawable): PageStateHolder {
        errorView.findViewById<ImageView>(R.id.iv_page_error).setImageDrawable(drawable)
        return this
    }

    fun setErrorViewImg(bitmap: Bitmap): PageStateHolder {
        errorView.findViewById<ImageView>(R.id.iv_page_error).setImageBitmap(bitmap)
        return this
    }

    fun setErrorViewImg(@DrawableRes resId: Int): PageStateHolder {
        errorView.findViewById<ImageView>(R.id.iv_page_error).setImageResource(resId)
        return this
    }

    fun setErrorViewText(text: CharSequence): PageStateHolder {
        errorView.findViewById<TextView>(R.id.tv_page_error).text = text
        return this
    }

    fun setErrorViewText(@StringRes resId: Int): PageStateHolder {
        errorView.findViewById<TextView>(R.id.tv_page_error).setText(resId)
        return this
    }

    fun setErrorButtonClickListener(l: View.OnClickListener): PageStateHolder {
        val errorButton = errorView.findViewById<Button>(R.id.btn_page_error)
        errorButton.visibility = View.VISIBLE
        errorButton.setOnClickListener(l)
        return this
    }

    fun setLoadViewBackground(color: Int): PageStateHolder {
        loadView.setBackgroundColor(color)
        return this
    }

    fun setEmptyViewBackground(color: Int): PageStateHolder {
        emptyView.setBackgroundColor(color)
        return this
    }

    fun setErrorViewBackground(color: Int): PageStateHolder {
        errorView.setBackgroundColor(color)
        return this
    }

    fun setBackgroundClickEnable(enable: Boolean): PageStateHolder {
        if (enable) {
            loadView.setOnClickListener(null)
            emptyView.setOnClickListener(null)
            errorView.setOnClickListener(null)
        } else {
            val listener = View.OnClickListener {}
            loadView.setOnClickListener(listener)
            emptyView.setOnClickListener(listener)
            errorView.setOnClickListener(listener)
        }
        return this
    }

    fun setLoadState(loadState: LoadState) {
        if (curState == loadState) return
        curState = loadState
        when (loadState) {
            LoadState.LOADING -> {
                rootView.visibility = View.VISIBLE
                loadView.visibility = View.VISIBLE
                emptyView.visibility = View.GONE
                errorView.visibility = View.GONE
            }
            LoadState.EMPTY -> {
                rootView.visibility = View.VISIBLE
                loadView.visibility = View.GONE
                emptyView.visibility = View.VISIBLE
                errorView.visibility = View.GONE
            }
            LoadState.ERROR -> {
                rootView.visibility = View.VISIBLE
                loadView.visibility = View.GONE
                emptyView.visibility = View.GONE
                errorView.visibility = View.VISIBLE
            }
            LoadState.SUCCESS ->
                rootView.visibility = View.GONE
        }
    }

    fun findViewById(id: Int): View? {
        return rootView.findViewById(id)
    }

    enum class LoadState {
        LOADING,
        EMPTY,
        ERROR,
        SUCCESS
    }
}