package com.marvel.marvelcharacters.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.marvel.domain.entities.DomainData
import com.marvel.domain.entities.Failure
import com.marvel.domain.entities.MarvelCharacter
import com.marvel.domain.usecases.CharacterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val characterUseCase: CharacterUseCase
) : ViewModel() {

    private val _characterListLD = MutableLiveData<DomainData>()
    private val _showLoaderLD = MutableLiveData<Boolean>()
    private val _internetErrorLD = MutableLiveData<Boolean>()

    val characterListLD : LiveData<DomainData> = _characterListLD
    val showLoaderLD : LiveData<Boolean> = _showLoaderLD
    val internetErrorLD : LiveData<Boolean> = _internetErrorLD

    var fromNavigation = false

    init {
        doCharactersCall()
    }

    fun getNextCharacters(offset: Int){
        doCharactersCall(offset)
    }

    private fun doCharactersCall(offset: Int = 0){
        _showLoaderLD.value = true
        viewModelScope.launch(Dispatchers.IO) {
            characterUseCase.run(CharacterUseCase.Params.params(0L, offset)).fold(
                { failure ->
                    viewModelScope.launch(Dispatchers.Main) {
                        _showLoaderLD.value = false
                        when(failure){
                            is Failure.NoInternetConnection -> _internetErrorLD.value = true // Manage empty state no internet
                            else -> _internetErrorLD.value = false // Manage other empty state
                        }
                    }
                },
                {
                    viewModelScope.launch(Dispatchers.Main) {
                        _showLoaderLD.value = false
                        _characterListLD.value = it ?: DomainData(0, emptyList())
                    }
                }
            )
        }
    }

    fun navigateToCharacter(navController: NavController, id: Long){
        fromNavigation = true
        navController.navigate(
            HomeFragmentDirections.actionNavigationHomeToCharacterFragment(id)
        )
    }
}