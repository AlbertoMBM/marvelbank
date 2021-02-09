package com.marvel.marvelcharacters.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.marvel.domain.entities.MarvelCharacter
import com.marvel.marvelcharacters.R
import com.marvel.marvelcharacters.databinding.CardCharacterBinding
import com.marvel.marvelcharacters.utils.getUri

class CharacterAdapter(
    private val charactersList: List<MarvelCharacter>,
    val adapterOnClick : (Long) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_character, parent, false
            )
        )
    }

    override fun getItemCount(): Int = charactersList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val character = charactersList[position]
        holder.bind(character)
    }

    fun appendElements(elements: List<MarvelCharacter>){
        (charactersList as MutableList).addAll(elements)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CardCharacterBinding.bind(itemView)
        fun bind(item: MarvelCharacter){
            binding.run {
                name.text = item.name
                description.visibility = if (item.description.isBlank()){
                    View.GONE
                }else{
                    View.VISIBLE
                }
                description.text = item.description
                image.load(getUri(item.thumbnail.path, "", item.thumbnail.extension))
                binding.root.setOnClickListener{
                    adapterOnClick(item.id)
                }
            }
        }
    }

}