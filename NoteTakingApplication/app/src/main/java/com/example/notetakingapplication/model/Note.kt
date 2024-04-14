package com.example.notetakingapplication.model


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo
    val noteTitle:String,
    val noteBody:String
):Parcelable
