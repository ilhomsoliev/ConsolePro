package com.ilhomsoliev.consolepro.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "page")
data class PageModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val platform: String,
    val markdown: String
) {
    companion object {
        fun mapToPage(name: String, platform: String, markdown: String) =
            PageModel(null, name, platform, markdown)
    }
}