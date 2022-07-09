package com.example.mvvmdemo.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("results")
    val characters: List<Character>
)