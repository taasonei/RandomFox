package com.github.taasonei.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavouriteFoxPhoto::class], version = 1)
abstract class FavouritesRoomDatabase : RoomDatabase() {

    abstract fun favouritesDao(): FavouritesDao

    companion object {
        private var INSTANCE: FavouritesRoomDatabase? = null

        fun getDatabase(context: Context): FavouritesRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavouritesRoomDatabase::class.java,
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