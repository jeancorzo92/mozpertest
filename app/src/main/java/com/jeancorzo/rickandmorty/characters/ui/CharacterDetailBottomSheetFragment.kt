package com.jeancorzo.rickandmorty.characters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jeancorzo.rickandmorty.databinding.FragmentCharacterDetailBinding

class CharacterDetailBottomSheetFragment : BottomSheetDialogFragment() {

    private var mBinding: FragmentCharacterDetailBinding? = null
    private val binding get() = mBinding!!
    private val args: CharacterDetailBottomSheetFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.character = args.character
    }
}