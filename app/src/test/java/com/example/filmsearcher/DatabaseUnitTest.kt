package com.example.filmsearcher

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.example.filmsearcher.model.Movie
import com.example.filmsearcher.model.db.MovieDao
import com.example.filmsearcher.model.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DatabaseUnitTest {
    private lateinit var movieDao: MovieDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext;
        movieDao = AppDatabase.createDb(context).movieDao()
    }

    @Test
    fun insert_movie_isCorrect() {
        val movie = Movie("Friends", 18507, "Serial",
                            "https://cs.pikabu.ru/post_img/big/2013/08/31/1/1377900959_732817382.jpg")
        GlobalScope.launch(Dispatchers.IO) {
            movieDao.insert(movie)
            val added_movie = movieDao.getMovieById(18507)
            movieDao.deletemovieById(added_movie.id)
            assertEquals(movie.title, added_movie.title)
        }
    }
}