package com.marvel.marvelcharacters.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marvel.domain.entities.ProductItem
import com.marvel.marvelcharacters.R
import com.marvel.marvelcharacters.databinding.CardComicSerieBinding

class SeriesComicsAdapter(
    private val productsList: List<ProductItem>
) : RecyclerView.Adapter<SeriesComicsAdapter.ItemViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_comic_serie, parent, false
            )
        )
    }

    override fun getItemCount(): Int = productsList.size

    override fun onBindViewHolder(holder: SeriesComicsAdapter.ItemViewHolder, position: Int) {
        val product = productsList[position]
        holder.bind(product)
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CardComicSerieBinding.bind(view)
        fun bind(item: ProductItem){
            binding.product.text = item.name
        }
    }

}