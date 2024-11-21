package com.cyberiyke.TravelApp.data.model

import com.google.gson.annotations.SerializedName



data class NewsResponse(
    @SerializedName("status")
    val status: String = "",

    @SerializedName("totalResults")
    val totalResults: Int = 0,

    @SerializedName("articles")
    val articles: List<com.cyberiyke.TravelApp.data.model.Article> = emptyList()
)


data class Article(
    @SerializedName("author")
    var author : String?,

    @SerializedName("content")
    var content: String?,

    @SerializedName("description")
    var description: String?,

    @SerializedName("publishedAt")
    var publishedAt: String?,

    @SerializedName("source")
    var source: com.cyberiyke.TravelApp.data.model.Source,

    @SerializedName("title")
    var title : String?,

    @SerializedName("url")
    var url: String?,

    @SerializedName("urlToImage")
    var urlToImage: String?

)

data class Source(
    @SerializedName("id")
    var id: String = "no_id",
    @SerializedName("name")
    var name: String = "no_name"
)