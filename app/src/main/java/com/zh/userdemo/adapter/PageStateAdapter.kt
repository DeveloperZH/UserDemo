package com.zh.userdemo.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zh.userdemo.R
import com.zh.userdemo.bean.UserBean

/**
 * @describe:
 * @author: Z H
 * @date: 2020/4/17 10:32
 * @pkgName: com.zh.userdemo.adapter
 */
class PageStateAdapter(data: MutableList<UserBean>? = null)
    : BaseQuickAdapter<UserBean, BaseViewHolder>(R.layout.item_page_state, data) {

    override fun convert(holder: BaseViewHolder, item: UserBean) {
        holder.setText(R.id.tv_name,item.name)
        holder.setText(R.id.tv_age,item.age.toString())
    }

}