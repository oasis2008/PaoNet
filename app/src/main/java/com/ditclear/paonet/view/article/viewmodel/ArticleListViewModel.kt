package com.ditclear.paonet.view.article.viewmodel

import android.databinding.ObservableArrayList
import com.ditclear.paonet.di.scope.FragmentScope
import com.ditclear.paonet.lib.extention.async
import com.ditclear.paonet.model.data.Article
import com.ditclear.paonet.model.remote.api.PaoService
import com.ditclear.paonet.viewmodel.PagedViewModel
import javax.inject.Inject

/**
 * 页面描述：ArticleListViewModel
 *
 * Created by ditclear on 2017/10/3.
 */
@FragmentScope
class ArticleListViewModel
@Inject
constructor( private val repo: PaoService) : PagedViewModel() {

    val obserableList = ObservableArrayList<Article>()

    fun loadData(isRefresh: Boolean) {
        startLoad(isRefresh)
        repo.getArticleList(page).compose(bindToLifecycle()).async(1000)
                .map { articleList ->
                    with(articleList) {
                        if (isRefresh) {
                            obserableList.clear()
                        }
                        loadMore.set(!incomplete_results)
                        return@map items?.let { obserableList.addAll(it) }
                    }
                }
                .subscribe{ t1, t2 -> loading.set(false) }
    }
}