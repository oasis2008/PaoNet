package com.ditclear.paonet.view.ui.home.viewmodel

import com.ditclear.paonet.model.data.Category

/**
 * 页面描述：CategoryItemViewModel
 *
 * Created by ditclear on 2017/11/5.
 */
class CategoryItemViewModel(val cate: Category) {

    var catename = cate.catename
    var value: Int? = cate.value
    var count: Int? = cate.count

}