package ru.androidschool.mylandmarks.network

data class LandmarkInfo(
    val title: String,
    val displaytitle: String,
    val thumbnail: Thumbnail,
    val extract: String
)

data class Thumbnail(
    val source: String
)