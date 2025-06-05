package com.example.diary

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ClothesTable")
data class Clothes(
    var name: String,
    var color: String,
    var type: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
