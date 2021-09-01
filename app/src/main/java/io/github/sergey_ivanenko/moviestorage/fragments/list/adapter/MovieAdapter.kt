package io.github.sergey_ivanenko.moviestorage.fragments.list.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.sergey_ivanenko.moviestorage.R
import io.github.sergey_ivanenko.moviestorage.data.model.Movie
import io.github.sergey_ivanenko.moviestorage.databinding.RecyclerviewItemBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var movies = emptyList<Movie>()
        set(newVale) {
            val diffCallback = MoviesDiffCallback(field, newVale)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newVale
            diffResult.dispatchUpdatesTo(this)
        }

    class MovieViewHolder(private val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var item: Movie? = null
            private set

        fun onBind(item: Movie) {
            this.item = item
            binding.movieTitleTextView.text = item.title
            binding.movieYearTextView.text = item.year.toString()
            binding.movieGenreTextView.text = item.genre
        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = RecyclerviewItemBinding.inflate(inflater, parent, false)

                return MovieViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.onBind(movie)
        holder.itemView.setOnClickListener {
            val args = Bundle()
            args.putParcelable("currentMovie", movie)
            it.findNavController().navigate(R.id.action_movieListFragment_to_updateMovieFragment, args)
        }
    }

    override fun getItemCount(): Int = movies.size

    // Delete in Future
    /*fun setDataMovies(newMovies: List<Movie>) {
        val diffCallback = MoviesDiffCallback(movies, newMovies)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
    }*/
}
