package io.github.sergey_ivanenko.moviestorage.fragments.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import io.github.sergey_ivanenko.moviestorage.R
import io.github.sergey_ivanenko.moviestorage.data.model.Movie
import io.github.sergey_ivanenko.moviestorage.data.viewmodel.MovieViewModel
import io.github.sergey_ivanenko.moviestorage.databinding.FragmentUpdateMovieBinding

class UpdateMovieFragment : Fragment() {

    private var _binding: FragmentUpdateMovieBinding? = null
    private val binding: FragmentUpdateMovieBinding get() = requireNotNull(_binding)

    private val movieViewModel: MovieViewModel by viewModels()

    private var _movie: Movie? = null
    private val movie: Movie get() = requireNotNull(_movie)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateMovieBinding.inflate(inflater, container, false)

        _movie = arguments?.getParcelable("currentMovie")

        binding.movieTitleEditText.setText(movie.title)
        binding.movieYearEditText.setText(movie.year.toString())
        binding.movieGenreEditText.setText(movie.genre)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        /*super.onCreateOptionsMenu(menu, inflater)*/
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_save) {
            updateItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateItem() {
        val movieTitle = binding.movieTitleEditText.text.toString()
        val movieYear = binding.movieYearEditText.text.toString()
        val movieGenre = binding.movieGenreEditText.text.toString()

        val validation = verifyDataFromUser(movieTitle, movieYear, movieGenre)

        if (validation) {
            // Update current item
            val updatedItem = Movie(
                movie.id,
                movieTitle,
                movieYear.toInt(),
                movieGenre
            )
            movieViewModel.updateMovie(updatedItem)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()
            // Navigate back to list movies
            findNavController().navigate(R.id.action_updateMovieFragment_to_movieListFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun verifyDataFromUser(title: String, year: String, genre: String): Boolean {
        return !(title.isEmpty() || year.isEmpty() || genre.isEmpty())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}