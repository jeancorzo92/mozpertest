package com.jeancorzo.rickandmorty.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jeancorzo.rickandmorty.R
import com.jeancorzo.rickandmorty.databinding.ItemLoadingBinding

class AppLoadStateAdapter(
    private val retryListener: RetryListener
) : LoadStateAdapter<LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding, retryListener)
    }

}

class LoadStateViewHolder(
    private val binding: ItemLoadingBinding,
    private val retryListener: RetryListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
        binding.run {
            binding.itemLoadingButton.setOnClickListener { retryListener.retry() }
            binding.itemLoadingText.text = if (loadState is LoadState.Error) {
                itemView.resources.getText(R.string.error_loading_data)
            } else {
                ""
            }
            itemLoadingProgress.visible(loadState is LoadState.Loading)
            itemLoadingText.visible(loadState is LoadState.Error)
            itemLoadingButton.visible(loadState is LoadState.Error)
        }
    }
}

