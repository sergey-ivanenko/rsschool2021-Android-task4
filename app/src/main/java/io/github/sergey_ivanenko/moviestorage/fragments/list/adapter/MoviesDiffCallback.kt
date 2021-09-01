package io.github.sergey_ivanenko.moviestorage.fragments.list.adapter

import androidx.recyclerview.widget.DiffUtil
import io.github.sergey_ivanenko.moviestorage.data.model.Movie

class MoviesDiffCallback(
    private val oldListMovies: List<Movie>,
    private val newListMovies: List<Movie>,
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldListMovies.size

    override fun getNewListSize(): Int = newListMovies.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldListMovies[oldItemPosition].id == newListMovies[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldListMovies[oldItemPosition] == newListMovies[newItemPosition]
    }
}
