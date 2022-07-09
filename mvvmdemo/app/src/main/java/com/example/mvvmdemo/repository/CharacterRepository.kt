package com.example.mvvmdemo.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmdemo.db.CharacterDatabase
import com.example.mvvmdemo.model.Character
import com.example.mvvmdemo.remote.CharacterApi
import com.example.mvvmdemo.utils.NetworkUtils
import com.example.mvvmdemo.utils.Resource
import retrofit2.HttpException
import java.io.IOException

private const val TAG = "CharacterRepository"

class CharacterRepository(
    private val applicationContext: Context,
    private val characterDatabase: CharacterDatabase,
    private val characterApi: CharacterApi
) {
    private val _characters = MutableLiveData<Resource<List<Character>>>()
    val characters: LiveData<Resource<List<Character>>> = _characters

    suspend fun getCharacters(page: Int = 1) {
        _characters.postValue(Resource.Loading())

        try {
            if (NetworkUtils.isInternetAvailable(applicationContext)) {
                val response = characterApi.getCharacters(page)
                if (response.isSuccessful && response.body() != null) {
                    val characters = response.body()!!.characters
                    characterDatabase.characterDao().addCharacters(characters)
                    _characters.postValue(Resource.Success(characters))
                } else {
                    _characters.postValue(Resource.Error("Something went wrong."))
                }
            } else {
                val characters = characterDatabase.characterDao().getCharacters()
                _characters.postValue(Resource.Success(characters))
            }
        } catch (e: IOException) {
            Log.e(TAG, "Failed to fetch characters: ${e.message}")
            _characters.postValue(Resource.Error("You might not have internet connection."))
        } catch (e: HttpException) {
            Log.e(TAG, "Failed to fetch characters: ${e.message}")
            _characters.postValue(Resource.Error("Unexpected response."))
        } catch (e: Exception) {
            Log.e(TAG, "Failed to fetch characters: ${e.message}")
            _characters.postValue(Resource.Error("Something went wrong."))
        }
    }
}