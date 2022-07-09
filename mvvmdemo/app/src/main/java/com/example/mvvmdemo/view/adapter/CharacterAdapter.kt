package com.example.mvvmdemo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mvvmdemo.databinding.ItemCharacterBinding
import com.example.mvvmdemo.model.Character
import com.example.mvvmdemo.view.adapter.CharacterAdapter.CharacterViewHolder

class CharacterAdapter : RecyclerView.Adapter<CharacterViewHolder>() {
    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.character = character
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)
    var characters: List<Character>
        set(newList) = differ.submitList(newList)
        get() = differ.currentList

    override fun getItemCount() = characters.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }
}