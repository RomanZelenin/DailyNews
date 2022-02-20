package com.romazelenin.news.data

import com.romazelenin.news.domain.ArticleCategory
import com.romazelenin.news.domain.Country
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("q") query: String,
        @Query("category") category: ArticleCategory,
        @Query("country") country: Country
    ): NewsResponse

    companion object {
        private val baseUrl = "https://newsapi.org/v2/"
        private val apiKey = "8f6a0df90924494d943da4b95e9efe8d"

        val INSTANCE: ApiService by lazy {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor {
                    val request = it.request().let {
                        val urlWithApiKey =
                            it.url.newBuilder()
                                .addQueryParameter("apiKey", apiKey)
                                .build()
                        it.newBuilder().url(urlWithApiKey).build()
                    }
                    it.proceed(request)
                }
                .build()

            val retrofit = Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()

            retrofit.create(ApiService::class.java)
        }
    }

    enum class Status {
        ok, error
    }
}