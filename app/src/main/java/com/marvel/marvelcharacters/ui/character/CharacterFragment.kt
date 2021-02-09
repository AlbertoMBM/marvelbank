package com.marvel.marvelcharacters.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.load
import com.marvel.domain.entities.MarvelCharacter
import com.marvel.marvelcharacters.R
import com.marvel.marvelcharacters.adapters.SeriesComicsAdapter
import com.marvel.marvelcharacters.databinding.FragmentCharacterBinding
import com.marvel.marvelcharacters.utils.getUri
import com.marvel.marvelcharacters.utils.showDateWithPattern
import com.marvel.marvelcharacters.utils.showLoader
import com.marvel.marvelcharacters.utils.transformDateFromServiceToDate
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterFragment : Fragment() {

    private val viewModel: CharacterViewModel by viewModel()
    private lateinit var binding: FragmentCharacterBinding
    private val args : CharacterFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCharacterBinding.bind(view)
        viewModel.getCharacterData(args.characterId)
        initObservers()
    }

    private fun initObservers(){
        viewModel.characterLD.observe(viewLifecycleOwner, Observer {
            setupView(it)
        })
        viewModel.showLoaderLD.observe(viewLifecycleOwner, Observer {
            showLoader(it, this)
        })
    }

    private fun setupView(character: MarvelCharacter){
        binding.image.load(getUri(
            character.thumbnail.path, "", character.thumbnail.extension
        ))
        binding.name.text = character.name
        binding.description.text = character.description
        binding.date.text = character.modified.transformDateFromServiceToDate().showDateWithPattern()
        binding.comics.adapter = SeriesComicsAdapter(character.comics.items)
        binding.series.adapter = SeriesComicsAdapter(character.series.items)
        if (character.description.isBlank()){
            binding.description.visibility = View.GONE
        }
    }

}