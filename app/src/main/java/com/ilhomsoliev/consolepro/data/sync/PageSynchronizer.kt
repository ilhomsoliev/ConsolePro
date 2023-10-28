package com.ilhomsoliev.consolepro.data.sync

import com.ilhomsoliev.consolepro.core.printToLog
import com.ilhomsoliev.consolepro.data.DataStoreManager
import com.ilhomsoliev.consolepro.data.local.PageDao
import com.ilhomsoliev.consolepro.domain.DownloadPages
import com.ilhomsoliev.consolepro.domain.ExtractPages
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.Duration
import java.time.Instant

class PageSynchronizer(
    private val downloadPages: DownloadPages,
    private val extractPages: ExtractPages,
    private val pageDao: PageDao,
    private val dataStoreManager: DataStoreManager
) {
    private val _state = MutableStateFlow<SyncState?>(null)
    val state = _state.asStateFlow()

    suspend fun sync(forceSync: Boolean = false) {
        val amount = pageDao.getPagesAmount()
        amount.printToLog()
        if (amount == 0) {
            _state.emit(SyncState.INITIAL_SYNC)
        } else if (forceSync || checkForUpdates()) {
            _state.emit(SyncState.SYNC)
        } else {
            _state.emit(SyncState.IDLE)
            return
        }
        val result = if (updatePages()) SyncState.IDLE else SyncState.FAILED
        _state.emit(result)
    }

    private suspend fun updatePages(): Boolean {
        val zipFile = try {
            downloadPages()
        } catch (e: Exception) {
            // logcat(LogPriority.WARN) { "Could not download pages" }
            return false
        }

        val pages = try {
            extractPages(zipFile)
        } catch (e: Exception) {
            // logcat(LogPriority.WARN) { "Could not unzip file" }
            return false
        }

        try {
            pageDao.insert(pages)
        } catch (e: Exception) {
            // logcat(LogPriority.WARN) { "Could not save pages" }
            return false
        }

        try {
            dataStoreManager.changeLastTimeSync(Instant.now().epochSecond)
        } catch (e: Exception) {
            // logcat(LogPriority.WARN) { "Could not log synchronization" }
            return false
        }

        // logcat(LogPriority.INFO) { "Page synchronization completed" }
        return true
    }

    private suspend fun checkForUpdates(): Boolean {
        try {
            val lastSync = Instant.ofEpochSecond(dataStoreManager.getLastTimeSync())
            val duration = Duration.between(lastSync, Instant.now()).toDays()

            if (duration > 45) return true
        } catch (e: Exception) {
            // logcat(LogPriority.WARN) { "Could not read last synchronization" }
        }
        return false
    }

    enum class SyncState {
        IDLE, INITIAL_SYNC, SYNC, FAILED
    }
}
