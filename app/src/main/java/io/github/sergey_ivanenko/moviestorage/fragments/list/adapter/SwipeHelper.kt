package io.github.sergey_ivanenko.moviestorage.fragments.list.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import io.github.sergey_ivanenko.moviestorage.data.model.Movie

class SwipeHelper(onSwiped: (Movie) -> Unit): ItemTouchHelper(SwipeToDeleteCallback(onSwiped))