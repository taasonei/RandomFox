package com.github.taasonei.randomfox.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseFox::class], version = 1)
abstract class FoxDatabase : RoomDatabase() {

    abstract fun favouritesDao(): FavouritesDao

    companion object {
        private var INSTANCE: FoxDatabase? = null

        fun getDatabase(context: Context): FoxDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoxDatabase::class.java,
                    "fox_photo_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }

}