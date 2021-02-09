package com.marvel.marvelcharacters.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marvel.domain.entities.DomainData
import com.marvel.marvelcharacters.R
import com.marvel.marvelcharacters.adapters.CharacterAdapter
import com.marvel.marvelcharacters.base.BaseFragment
import com.marvel.marvelcharacters.databinding.FragmentHomeBinding
import com.marvel.marvelcharacters.utils.showLoader
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var lm : LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return getPersistentView(inflater, container, savedInstanceState, R.layout.fragment_home)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHomeBinding.bind(view)
        if (!hasInitializedRootView) {
            hasInitializedRootView = true
        }
        initObservers()
    }

    private fun initObservers(){
        homeViewModel.characterListLD.observe(viewLifecycleOwner, Observer {
            if (binding.characters.adapter == null){
                setupRecyclerView(it)
            }else{
                loadMoreItems(it)
            }
        })
        homeViewModel.showLoaderLD.observe(viewLifecycleOwner, Observer { toShow ->
            showLoader(toShow, this)
        })
        homeViewModel.internetErrorLD.observe(viewLifecycleOwner, Observer { isInternetError ->
            val message = if (isInternetError){
                getString(R.string.internet_not_available)
            }else{
                getString(R.string.other_message_error)
            }
            manageError(message)
        })
    }

    private fun setupRecyclerView(data: DomainData){
        lm = LinearLayoutManager(context)
        binding.characters.layoutManager = lm
        binding.characters.adapter = CharacterAdapter(data.results) {
            onClickCharacter(it)
        }
        initScrollListener()
    }

    private fun loadMoreItems(data: DomainData){
        (binding.characters.adapter as CharacterAdapter).appendElements(data.results)
    }

    private fun initScrollListener(){
        binding.characters.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                binding.characters.adapter?.let {
                    if (lm.findLastCompletelyVisibleItemPosition() == it.itemCount - 1){
                        homeViewModel.getNextCharacters(it.itemCount)
                    }
                }
            }
        })
    }

    private fun manageError(message: String){
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.error))
            .setMessage(message)
            .setPositiveButton(
                getString(R.string.ok)
            ) { _, _ ->  }
            .show()
    }

    private fun onClickCharacter(id: Long){
        homeViewModel.navigateToCharacter(findNavController(), id)
    }
}