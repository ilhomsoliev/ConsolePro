package com.ilhomsoliev.consolepro.presentation.page.viewmodel

import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.ViewModel
import com.ilhomsoliev.consolepro.data.local.PageDao
import com.ilhomsoliev.consolepro.domain.MarkdownParser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PageDetailsViewModel(
    private val store: PageDao,
    private val parser: MarkdownParser,
) : ViewModel() {

    private val _pageName = MutableStateFlow<String>("")
    val pageName = _pageName.asStateFlow()

    private val _pageContent = MutableStateFlow<AnnotatedString?>(null)
    val pageContent = _pageContent.asStateFlow()


    suspend fun queryPage(pageId: Int) {
        val page = store.getPageById(pageId)
        page?.let {
            _pageName.value = page.name
            _pageContent.value = parser(page.markdown)
        }
    }

}