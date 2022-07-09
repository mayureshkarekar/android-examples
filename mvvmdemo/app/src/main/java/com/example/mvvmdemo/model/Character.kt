package com.example.mvvmdemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val TABLE_NAME_CHARACTER = "character"

@Entity(tableName = TABLE_NAME_CHARACTER)
data class Character(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Long,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "species")
    @SerializedName("species")
    val species: String,

    @ColumnInfo(name = "image")
    @SerializedName("image")
    val image: String
)