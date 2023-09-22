package com.ilhomsoliev.consolepro.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [PageModel::class],
    version = 1,
    exportSchema = false
)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun pageDao(): PageDao

}
@Volatile
private var INSTANCE: ApplicationDatabase? = null

fun getDatabaseInstance(context: Context) =
    INSTANCE ?: synchronized(context) {
        val instance =
            Room.databaseBuilder(
                context,
                ApplicationDatabase::class.java,
                "pages_database"
            )
                /*   .addCallback(object : Callback() {
                       override fun onCreate(db: SupportSQLiteDatabase) {
                           super.onCreate(db)

                       }
                   })*/
                .fallbackToDestructiveMigration()
                .build()
                .also {
                    INSTANCE = it
                }
        instance
    }