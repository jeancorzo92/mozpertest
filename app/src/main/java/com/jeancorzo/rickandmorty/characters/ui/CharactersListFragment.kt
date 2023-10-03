package com.jeancorzo.rickandmorty.characters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.jeancorzo.rickandmorty.R
import com.jeancorzo.rickandmorty.characters.domain.model.Character
import com.jeancorzo.rickandmorty.databinding.FragmentCharactersBinding
import com.jeancorzo.rickandmorty.utils.AppLoadStateAdapter
import com.jeancorzo.rickandmorty.utils.GridSpanSizeLookup
import com.jeancorzo.rickandmorty.utils.ItemOffsetDecoration
import com.jeancorzo.rickandmorty.utils.visible
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersListFragment : Fragment() {

    private var mBinding: FragmentCharactersBinding? = null
    private val binding get() = mBinding!!
    private val viewModel: CharactersViewModel by viewModel()
    private val characterListAdapter = CharacterListPagingAdapter { character ->
        navigateToCharacterDetail(character)
    }
    private val headerAdapter = AppLoadStateAdapter(characterListAdapter::retry)
    private val footerAdapter = AppLoadStateAdapter(characterListAdapter::retry)


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
        observeData()
    }

    private fun setUpRecyclerView() {
        val layoutManager = GridLayoutManager(requireContext(), 2).apply {
            spanSizeLookup =
                GridSpanSizeLookup(2, characterListAdapter, headerAdapter, footerAdapter)
        }
        val itemDecoration = ItemOffsetDecoration(requireContext(), R.dimen.recycler_item_offset)
        binding.charactersRecyclerView.adapter =
            characterListAdapter.withLoadStateHeaderAndFooter(headerAdapter, footerAdapter)
        binding.charactersRecyclerView.layoutManager = layoutManager
        binding.charactersRecyclerView.addItemDecoration(itemDecoration)
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.characterList.collectLatest {
                    characterListAdapter.submitData(it)
                }

                characterListAdapter.loadStateFlow.collect { loadState ->
                    handleLoadState(loadState)
                }
            }

        }


    }

    private fun handleLoadState(loadState: CombinedLoadStates) {
        headerAdapter.loadState = loadState.mediator
            ?.refresh
            ?.takeIf { it is LoadState.Error && characterListAdapter.itemCount > 0 }
            ?: loadState.prepend

        val isListEmpty = loadState.refresh is LoadState.NotLoading && characterListAdapter.itemCount == 0
        val listIsVisible = loadState.source.refresh is LoadState.NotLoading || loadState.mediator?.refresh is LoadState.NotLoading

        binding.charactersEmptyList.visible(isListEmpty)
        binding.charactersRecyclerView.visible(listIsVisible)
    }

    private fun navigateToCharacterDetail(character: Character) {
        findNavController().navigate(
            CharactersListFragmentDirections.toCharacterDetail(
                title = character.name,
                character
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}