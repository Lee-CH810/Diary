package com.example.diary

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ClothesDao {
    @Insert
    fun insert(clothes: Clothes)

    @Update
    fun update(clothes: Clothes)

    @Delete
    fun delete(clothes: Clothes)

    /**
     * 단일 조회
     */
    @Query("SELECT id, name, color, type FROM ClothesTable WHERE id = :id")
    fun getClothe(id: Int): Clothes

    /**
     * 테이블 전체 조회
     */
    @Query("SELECT id, name, color, type FROM ClothesTable")
    fun getClothes(): List<Clothes>
}