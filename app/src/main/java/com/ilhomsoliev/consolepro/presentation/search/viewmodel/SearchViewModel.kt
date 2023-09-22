package com.ilhomsoliev.consolepro.presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.consolepro.data.local.PageDao
import com.ilhomsoliev.consolepro.data.local.PageModel
import com.ilhomsoliev.consolepro.data.model.Icon
import com.ilhomsoliev.consolepro.data.model.PageItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val store: PageDao
) : ViewModel() {
    private val recentPages = MutableStateFlow<List<PageItem>>(emptyList())

    private val _resultsPages = MutableStateFlow<List<PageItem>>(emptyList())
    val resultsPages = _resultsPages.asStateFlow()

    fun querySearch(term: String) = viewModelScope.launch {
        if (term.isEmpty()) {
            _resultsPages.value = recentPages.value
            // _uiState.update { SearchResult(recentPages.value) }
        } else {
            store.queryPages(term).collect { queryPages ->
                _resultsPages.value = queryPages mergeWith recentPages.value
                /*if (queryPages.isEmpty()) {

                    _uiState.update { NoResult }
                } else {
                    _uiState.update { SearchResult(queryPages mergeWith recentPages.value) }
                }*/
            }
        }
    }

    // Merge query pages and recent pages to one list
    private infix fun List<PageModel>.mergeWith(recentPages: List<PageItem>): List<PageItem> {
        val searchResult = this.map { page -> PageItem(page, Icon.SEARCH_RESULT) }
        val diff = MAX_LIST_ITEMS - searchResult.size.coerceAtMost(MAX_LIST_ITEMS)
        return searchResult + recentPages.take(diff)
    }

    init {
        viewModelScope.launch {
            store.getRecentSearches().collect { pages ->
                recentPages.update {
                    pages.map { page ->
                        PageItem(page, Icon.RECENT_PAGE)
                    }
                }
                _resultsPages.value = recentPages.value
                // _uiState.update { SearchResult(recentPages.value) }
            }
        }
    }
}

private const val MAX_LIST_ITEMS = 8
