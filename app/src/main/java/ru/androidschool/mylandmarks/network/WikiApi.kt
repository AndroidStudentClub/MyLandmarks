package ru.androidschool.mylandmarks.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WikiApi {
    @GET("rest_v1/page/summary/{query}")
    fun getLandmarkInfo(
        @Path("query") query: String
    ): Call<LandmarkInfo>
}