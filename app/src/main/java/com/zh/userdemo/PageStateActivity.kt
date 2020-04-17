package com.zh.userdemo

import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.tsingning.robot.ui.PageStateHolder
import com.zh.userdemo.adapter.PageStateAdapter
import com.zh.userdemo.bean.UserBean
import kotlinx.android.synthetic.main.activity_page_state.*

/**
 * @describe: PageState
 * @author: Z H
 * @date: 2020/4/17 10:12
 * @pkgName: com.zh.userdemo
 */
class PageStateActivity : BaseActivity() {

    private lateinit var userList: MutableList<UserBean>
    private var mPagetateAdapter: PageStateAdapter? = null
    private lateinit var mPageStateHolder: PageStateHolder

    override fun layoutResId(): Int {
        return R.layout.activity_page_state
    }


    override fun initData() {
        mRv.layoutManager = LinearLayoutManager(this)
        userList = ArrayList();
        mPageStateHolder = PageStateHolder(this,tvTitle)

        mPageStateHolder.setLoadState(PageStateHolder.LoadState.LOADING)
        Handler().postDelayed(object : Runnable {
            override fun run() {
                runOnUiThread {
                    for (index in 1..10) {
                        val userBean = UserBean("我是第$index 项", index);
                        userList.add(userBean)
                        mPageStateHolder.setLoadState(PageStateHolder.LoadState.SUCCESS)
                    }
                    mPagetateAdapter = PageStateAdapter(userList)
                    mRv.adapter = mPagetateAdapter
                }
            }

        }, 3000)
    }

    override fun initEvent() {
        /**
         * Empty 状态
         * ERROR 类似用法
         * */
        tvTitle.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                userList.clear()
                mPagetateAdapter?.notifyDataSetChanged()
                mPageStateHolder.setLoadState(PageStateHolder.LoadState.EMPTY)
            }
        })
    }


}