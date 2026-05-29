package com.marijamihajlovska.gymtraininggenerator.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WgerApiService {
    @GET("api/v2/exerciseinfo/")
    suspend fun getExercises(
        @Query("format") format: String = "json",
        @Query("language") language: Int = 2,
        @Query("limit") limit: Int = 80,
        @Query("offset") offset: Int = 0
    ): WgerPagedResponse
}
