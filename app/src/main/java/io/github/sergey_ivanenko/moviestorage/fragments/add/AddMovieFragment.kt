package io.github.sergey_ivanenko.moviestorage.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import io.github.sergey_ivanenko.moviestorage.R
import io.github.sergey_ivanenko.moviestorage.data.model.Movie
import io.github.sergey_ivanenko.moviestorage.data.viewmodel.MovieViewModel
import io.github.sergey_ivanenko.moviestorage.databinding.FragmentAddMovieBinding

class AddMovieFragment : Fragment() {

    private var _binding: FragmentAddMovieBinding? = null
    private val binding: FragmentAddMovieBinding get() = requireNotNull(_binding)

    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddMovieBinding.inflate(inflater, container, false)

        binding.addMovieButton.setOnClickListener {
            insertMovieToDb()
        }

        return binding.root
    }

    private fun insertMovieToDb() {
        val mTitle = binding.movieTitleEditText.text.toString()
        val mYear = binding.movieYearEditText.text.toString()
        val mGenre = binding.movieGenreEditText.text.toString()

        val validation = verifyDataFromUser(mTitle, mYear, mGenre)

        if (validation) {
            val newMovie = Movie(0, mTitle, mYear.toInt(), mGenre)
            movieViewModel.addMovie(newMovie)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addMovieFragment_to_movieListFragment)
        } else {
            Toast.makeText(requireContext(), "Please, fill out all fields.", Toast.LENGTH_SHORT).show()
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