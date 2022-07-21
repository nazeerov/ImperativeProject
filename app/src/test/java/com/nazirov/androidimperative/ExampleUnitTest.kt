package com.nazirov.androidimperative

import com.nazirov.androidimperative.di.AppModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun responseIsSuccesfull()= runTest{
        val response=AppModule().tvShowService().apiTVShowPopular(1)
        assertEquals(response.code(),200)
        // assertTrue(response.isSuccessful)
    }

    @Test
    fun responseIsSuccesfull2()= runTest{
        val response=AppModule().tvShowService().apiTVShowDetails(1)
        assertEquals(response.code(),200)
        // assertTrue(response.isSuccessful)
    }



    @Test
    fun checkTVShowListIsNotNull()= runTest{
        val response=AppModule().tvShowService().apiTVShowPopular(1)
        assertNotNull(response.body())
        assertNotNull(response.body()!!.tv_shows)
    }
    @Test
    fun checkTVShowListIsNotNull2()= runTest{
        val response=AppModule().tvShowService().apiTVShowDetails(1)
        assertNotNull(response.body())
        assertNotNull(response.body()!!.tvShow)
    }

    @Test
    fun checkTVShowListSize()= runTest{
        val response=AppModule().tvShowService().apiTVShowPopular(1)
        val tvShowPopular = response.body()
        assertEquals(tvShowPopular!!.tv_shows.size,20)
    }

    @Test
    fun checkTVShowListSize2()= runTest{
        val response=AppModule().tvShowService().apiTVShowDetails(1)
        val tvShowPopular = response.body()
        assertEquals(tvShowPopular!!.tvShow.status,"Ended")
    }

    @Test
    fun checkFirstShowStatus()= runTest{
        val response=AppModule().tvShowService().apiTVShowPopular(1)
        val tvShowPopular = response.body()
        val tvShows = tvShowPopular!!.tv_shows
        val tvShow = tvShows[0]
        assertTrue(tvShow.status.equals("Running"))
    }

}