package io.github.sergey_ivanenko.moviestorage.fragments.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.sergey_ivanenko.moviestorage.R
import io.github.sergey_ivanenko.moviestorage.data.viewmodel.MovieViewModel
import io.github.sergey_ivanenko.moviestorage.databinding.FragmentMovieListBinding
import io.github.sergey_ivanenko.moviestorage.fragments.list.adapter.MovieAdapter
import io.github.sergey_ivanenko.moviestorage.fragments.list.adapter.SwipeHelper

class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding: FragmentMovieListBinding get() = requireNotNull(_binding)

    private val movieViewModel: MovieViewModel by viewModels()
    private val adapter: MovieAdapter by lazy { MovieAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)

        setupRecyclerView()

        movieViewModel.getAllMovies.observe(viewLifecycleOwner, Observer { movies ->
            adapter.movies = movies
        })

        binding.addNewMovieButton.setOnClickListener {
            findNavController().navigate(R.id.action_movieListFragment_to_addMovieFragment)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        SwipeHelper { movieViewModel.deleteMovie(it)}.attachToRecyclerView(recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.movie_list_fragment_menu, menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
