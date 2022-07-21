package com.nazirov.androidimperative.repository

import com.nazirov.androidimperative.networking.TVShovService
import javax.inject.Inject

class TVShowRepository @Inject constructor(private val tvShovService: TVShovService) {

    /**
     * Retrofit related
     */

    suspend fun apiTVShowPopular(page:Int)=tvShovService.apiTVShowPopular(page)
    suspend fun apiTVShowDetails(q:Int)=tvShovService.apiTVShowDetails(q)


    /**
     * Room related
     */

}