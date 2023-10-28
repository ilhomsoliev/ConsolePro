package com.ilhomsoliev.consolepro.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.consolepro.data.local.PageDao
import com.ilhomsoliev.consolepro.data.model.Icon
import com.ilhomsoliev.consolepro.data.model.PageItem
import com.ilhomsoliev.consolepro.data.sync.PageSynchronizer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(
    private val store: PageDao,
    private val synchronizer: PageSynchronizer
) : ViewModel() {

    private val _showToolbar = MutableStateFlow<Boolean>(false)
    val showToolbar = _showToolbar.asStateFlow()

    private val _pages = MutableStateFlow<List<PageItem>>(emptyList())
    val pages = _pages.asStateFlow()

    private suspend fun uiState(showToolbar: Boolean = true) {
        store.getRecentSearches().onEach { frequentPages ->
            if (frequentPages.size > 3) {
                val pages = frequentPages.map { page ->
                    PageItem(page, Icon.FREQUENT_PAGE)
                }
                _showToolbar.value = showToolbar
                _pages.value = pages
            } else {
                _showToolbar.value = showToolbar
                _pages.value = emptyList()
            }
        }.launchIn(viewModelScope)
    }


    private fun collectSyncState() {
        synchronizer.state.onEach { state ->
            state?.let {
                if (state == PageSynchronizer.SyncState.INITIAL_SYNC) {
                    uiState(showToolbar = false)
                } else {
                    uiState()
                }
            }
        }.launchIn(viewModelScope)
    }

    init {
        collectSyncState()
        viewModelScope.launch(Dispatchers.IO) {
            synchronizer.sync()
        }
    }

}