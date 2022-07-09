package com.example.mvvmdemo.view.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmdemo.Application
import com.example.mvvmdemo.R
import com.example.mvvmdemo.databinding.ActivityMainBinding
import com.example.mvvmdemo.utils.Resource
import com.example.mvvmdemo.view.adapter.CharacterAdapter
import com.example.mvvmdemo.viewmodel.MainViewModel
import com.example.mvvmdemo.viewmodel.MainViewModelFactory

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var characterAdapter: CharacterAdapter
    private val mainViewModel: MainViewModel by viewModels {
        val characterRepository = (application as Application).characterRepository
        MainViewModelFactory(characterRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvCharacters.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            )
            characterAdapter = CharacterAdapter()
            adapter = characterAdapter
        }

        mainViewModel.characters.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    binding.rvCharacters.isVisible = false
                    binding.tvMessage.isVisible = false
                    binding.progressBar.isVisible = true
                }

                is Resource.Error -> {
                    binding.rvCharacters.isVisible = false
                    binding.progressBar.isVisible = false
                    binding.tvMessage.isVisible = true
                    binding.tvMessage.text = getString(R.string.something_went_wrong)
                    Log.e(TAG, "Failed to fetch characters: ${it.message}")
                }

                is Resource.Success -> {
                    it.data?.let { characters ->
                        if (characters.isNotEmpty()) {
                            binding.progressBar.isVisible = false
                            binding.tvMessage.isVisible = false
                            binding.rvCharacters.isVisible = true
                            characterAdapter.characters = characters
                        } else {
                            binding.rvCharacters.isVisible = false
                            binding.progressBar.isVisible = false
                            binding.tvMessage.isVisible = true
                            binding.tvMessage.text = getString(R.string.no_data_found)
                        }
                    }
                }
            }
        }
    }
}