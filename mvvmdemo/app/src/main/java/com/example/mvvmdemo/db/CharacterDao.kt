package com.example.mvvmdemo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmdemo.model.Character
import com.example.mvvmdemo.model.TABLE_NAME_CHARACTER

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(characters: List<Character>)

    @Query("SELECT * FROM $TABLE_NAME_CHARACTER")
    suspend fun getCharacters(): List<Character>
}