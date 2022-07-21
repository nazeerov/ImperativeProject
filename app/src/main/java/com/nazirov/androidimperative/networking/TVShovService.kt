package com.nazirov.androidimperative.networking
import com.nazirov.androidimperative.model.TVShowDetails
import com.nazirov.androidimperative.model.TVShowPopular
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TVShovService {

    @GET("api/most-popular")
        suspend fun apiTVShowPopular(@Query("page") page:Int):Response<TVShowPopular>

        @GET("api/show-details")
        suspend fun apiTVShowDetails(@Query("q") q:Int):Response<TVShowDetails>
    }
