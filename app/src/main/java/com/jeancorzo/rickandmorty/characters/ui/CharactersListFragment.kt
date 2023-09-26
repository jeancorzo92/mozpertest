package com.jeancorzo.rickandmorty.characters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.jeancorzo.rickandmorty.R
import com.jeancorzo.rickandmorty.characters.model.Character
import com.jeancorzo.rickandmorty.databinding.FragmentCharactersBinding
import com.jeancorzo.rickandmorty.utils.AppLoadStateAdapter
import com.jeancorzo.rickandmorty.utils.GridSpanSizeLookup
import com.jeancorzo.rickandmorty.utils.ItemOffsetDecoration
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
        observeViewModel()
    }

    private fun setUpRecyclerView() {
        val layoutManager = GridLayoutManager(requireContext(), 2).apply {
            spanSizeLookup = GridSpanSizeLookup(2, characterListAdapter, footerAdapter)
        }
        val itemDecoration = ItemOffsetDecoration(requireContext(), R.dimen.recycler_item_offset)
        binding.charactersRecyclerView.adapter = characterListAdapter.withLoadStateFooter(footerAdapter)
        binding.charactersRecyclerView.layoutManager = layoutManager
        binding.charactersRecyclerView.addItemDecoration(itemDecoration)
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.characterList
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest {
                    characterListAdapter.submitData(it)
                }
        }
    }

    private fun navigateToCharacterDetail(character: Character) {
        findNavController().navigate(CharactersListFragmentDirections.toCharacterDetail(title = character.name, character))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}