package io.github.sergey_ivanenko.moviestorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.j256.ormlite.dao.Dao
import io.github.sergey_ivanenko.moviestorage.data.dao.DatabaseHelper
import io.github.sergey_ivanenko.moviestorage.data.model.Movie

class MainActivity : AppCompatActivity() {

    private val databaseHelper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)


        val moviesDao: Dao<Movie, Int> = databaseHelper.getMovieDao()
        /*val listMovies = moviesDao.queryForAll()*/

        val createButton = findViewById<Button>(R.id.createButton).setOnClickListener {

            moviesDao.create(Movie(0, "Back to the future 1", 1985, "Fantasy"))
            moviesDao.create(Movie(0, "Back to the future 2", 1989, "Fantasy"))
            moviesDao.create(Movie(0, "Back to the future 3", 1990, "Fantasy"))

        }

        val removeButton = findViewById<Button>(R.id.removeButton).setOnClickListener {
            moviesDao.delete(moviesDao.queryForAll())
        }

        val queryButton = findViewById<Button>(R.id.queryButton).setOnClickListener {
            textView.text = moviesDao.queryForAll().toString()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        databaseHelper.close()
    }
}