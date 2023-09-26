package com.jeancorzo.rickandmorty.characters.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jeancorzo.rickandmorty.characters.model.Character
import com.jeancorzo.rickandmorty.databinding.ItemCharacterBinding

class CharactersRecyclerAdapter(
    characterList: List<Character>
) : RecyclerView.Adapter<CharactersRecyclerAdapter.CharacterViewHolder>() {

    private var mCharacterList = ArrayList(characterList)
    private lateinit var binding: ItemCharacterBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun getItemCount(): Int = mCharacterList.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(mCharacterList[position])
    }

    fun setCharacterList(characterList: List<Character>) {
        mCharacterList = ArrayList(characterList)
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(character: Character) {
                binding.character = character
            }
        }
}