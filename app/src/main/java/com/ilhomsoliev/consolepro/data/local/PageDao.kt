package com.ilhomsoliev.consolepro.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: PageModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: List<PageModel>)
    @Delete
    suspend fun delete(data: PageModel)

    @Query("SELECT COUNT(*) from page")
    suspend fun getPagesAmount(): Int

    @Query("SELECT * from page")
    suspend fun getRecentSearches(): Flow<List<PageModel>>

    @Query("SELECT * FROM `page` WHERE id = :pageId")
    suspend fun getPageById(pageId: Int): PageModel?

    @Query(
        "SELECT * \n" +
                "FROM page\n" +
                "WHERE name LIKE :query;"
    ) // TODO add markdown field as query
    suspend fun queryPages(query: String): Flow<List<PageModel>>

}