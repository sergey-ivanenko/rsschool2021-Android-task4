package io.github.sergey_ivanenko.moviestorage.fragments.list.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import io.github.sergey_ivanenko.moviestorage.data.model.Movie

class SwipeToDeleteCallback(private val onSwiped: (Movie) -> Unit): ItemTouchHelper.SimpleCallback(
    0,
    ItemTouchHelper.LEFT
) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder,
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        (viewHolder as MovieAdapter.MovieViewHolder).item?.let { onSwiped(it) }
    }
}
