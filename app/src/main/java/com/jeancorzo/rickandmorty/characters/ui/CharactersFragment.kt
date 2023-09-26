package com.jeancorzo.rickandmorty.characters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.jeancorzo.rickandmorty.R
import com.jeancorzo.rickandmorty.characters.model.Character
import com.jeancorzo.rickandmorty.databinding.FragmentCharactersBinding
import com.jeancorzo.rickandmorty.utils.ItemOffsetDecoration
import com.jeancorzo.rickandmorty.utils.gone
import com.jeancorzo.rickandmorty.utils.visible
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment() {

    private var mBinding: FragmentCharactersBinding? = null
    private val binding get() = mBinding!!
    private val viewModel: CharactersViewModel by viewModel()
    private val charactersRecyclerAdapter by lazy { CharactersRecyclerAdapter(listOf()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        observeViewModel()
    }

    private fun setUpRecyclerView() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        val itemDecoration = ItemOffsetDecoration(requireContext(), R.dimen.recycler_item_offset)
        binding.charactersRecyclerView.adapter = charactersRecyclerAdapter
        binding.charactersRecyclerView.layoutManager = layoutManager
        binding.charactersRecyclerView.addItemDecoration(itemDecoration)
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { uiState ->
                    when (uiState) {
                        is CharactersUiState.DisplayCharacters -> showCharacterList(uiState.characterList)
                        is CharactersUiState.AddCharacters -> addCharactersToList(uiState.characterList)
                        CharactersUiState.ErrorLoadingCharacters -> showErrorView()
                        CharactersUiState.Loading -> showLoadingView()
                    }
                }
        }
    }

    private fun showCharacterList(characterList: List<Character>) {
        binding.charactersLoading.viewLoading.gone()
        charactersRecyclerAdapter.setCharacterList(characterList)
    }

    private fun addCharactersToList(characterList: List<Character>) {
        charactersRecyclerAdapter.addCharacters(characterList)
    }

    private fun showErrorView() {
        binding.charactersLoading.viewLoading.gone()
    }

    private fun showLoadingView() {
        binding.charactersLoading.viewLoading.visible()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}