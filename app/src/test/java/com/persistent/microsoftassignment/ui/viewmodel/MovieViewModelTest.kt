package com.persistent.microsoftassignment.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.persistent.microsoftassignment.TestUtil
import com.persistent.microsoftassignment.api.RestInterface
import com.persistent.microsoftassignment.database.AssignmentDatabase
import com.persistent.microsoftassignment.database.Repository
import com.persistent.microsoftassignment.ui.main.videos.VideoViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MovieViewModelTest {



    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: Repository
    private lateinit var viewModel: VideoViewModel
    private val database = mock(AssignmentDatabase::class.java, RETURNS_DEEP_STUBS)
    private val service = mock(RestInterface::class.java)
    private val resultDetails = TestUtil.createMovies()
    @Before
    fun setup() {
        repository = Repository(database.movieDao(), service)
        viewModel = VideoViewModel(repository,service)
    }

    @Test
    @Throws(Exception::class)
    fun insertAndLoad() = runBlockingTest {
        val movies = TestUtil.createMovies()
        database.movieDao().insertMovies(movies)
    }

    @Test
    fun fetchMoviesTest() = runBlockingTest {
        `when`(database.movieDao().getMovieDetails()).thenReturn(resultDetails)

        val value = database.movieDao().getMovieDetails()
        assertThat(value?.get(0)?.id, `is`(resultDetails.get(0).id))
        assertThat(value?.get(0)?.overview, `is`(resultDetails.get(0).overview))
        assertThat(value?.get(0)?.release_date, `is`(resultDetails.get(0).release_date))
        assertThat(value?.get(0)?.poster_path, `is`(resultDetails.get(0).poster_path))
    }

}