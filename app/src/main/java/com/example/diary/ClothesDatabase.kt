package com.example.diary

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Clothes::class], version = 1)
abstract class ClothesDatabase: RoomDatabase() {
    abstract fun clothesDao(): ClothesDao

    companion object {
        private var instance: ClothesDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ClothesDatabase? {
            if (instance == null) {
                synchronized(ClothesDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ClothesDatabase::class.java,
                        "clothes-database"//다른 데이터 베이스랑 이름겹치면 꼬임
                    ).allowMainThreadQueries().build()
                }
            }

            return instance
        }
    }

}