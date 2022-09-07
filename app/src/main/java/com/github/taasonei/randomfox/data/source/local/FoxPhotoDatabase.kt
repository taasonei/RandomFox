package com.github.taasonei.randomfox.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.taasonei.randomfox.data.source.model.local.FavouriteFoxPhoto

@Database(entities = [FavouriteFoxPhoto::class], version = 1)
abstract class FoxPhotoDatabase : RoomDatabase() {

    abstract fun favouritesFoxPhotoDao(): FavouritesFoxPhotoDao

    companion object {
        private var INSTANCE: FoxPhotoDatabase? = null

        fun getDatabase(context: Context): FoxPhotoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoxPhotoDatabase::class.java,
                    "fox_photo_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }

}
