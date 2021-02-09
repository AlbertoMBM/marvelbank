package com.marvel.marvelcharacters.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marvel.domain.entities.MarvelCharacter
import com.marvel.domain.usecases.CharacterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val characterUseCase: CharacterUseCase
) : ViewModel() {

    private val _characterLD = MutableLiveData<MarvelCharacter>()
    val characterLD : LiveData<MarvelCharacter> = _characterLD
    private val _showLoaderLD = MutableLiveData<Boolean>()
    val showLoaderLD : LiveData<Boolean> = _showLoaderLD

    fun getCharacterData(characterId: Long){
        _showLoaderLD.value = true
        viewModelScope.launch(Dispatchers.IO) {
            characterUseCase.run(CharacterUseCase.Params.params(characterId)).fold(
                {
                    viewModelScope.launch(Dispatchers.Main) {
                        _showLoaderLD.value = false
                    }
                },
                { data ->
                    viewModelScope.launch(Dispatchers.Main) {
                        _showLoaderLD.value = false
                        data?.results?.let { list ->
                            if (list.isNotEmpty()){
                                _characterLD.value = list.first()
                            }
                        }
                    }
                }
            )
        }
    }

}