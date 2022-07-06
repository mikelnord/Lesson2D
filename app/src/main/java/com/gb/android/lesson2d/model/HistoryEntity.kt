package com.gb.android.lesson2d.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(indices = [Index(value = arrayOf("word"), unique = true)])
class HistoryEntity (
    val word: String,
    val description: String?
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var wordId: Long = 0
}