package com.ilhomsoliev.consolepro.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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

    @Query("SELECT * from page LIMIT 10")
    fun getRecentSearches(): Flow<List<PageModel>>

    @Query("SELECT * FROM `page` WHERE id = :pageId")
    suspend fun getPageById(pageId: Int): PageModel

   @Query(
        "SELECT * \n" +
                "FROM page\n" +
                "WHERE name LIKE :query LIMIT 10"
    ) // TODO add markdown field as query
    fun queryPages(query: String): Flow<List<PageModel>>

}