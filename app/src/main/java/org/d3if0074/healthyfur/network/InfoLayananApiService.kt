package org.d3if0074.healthyfur.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if0074.healthyfur.model.InfoLayanan
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "silvyanarisma/json-assessment3/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface InfoLayananApiService {
    @GET("jenis-layanan.json")
    suspend fun getInfoLayanan(): List<InfoLayanan>
}
object InfoLayananApi {
    val service: InfoLayananApiService by lazy {
        retrofit.create(InfoLayananApiService::class.java)
    }

    fun getInfoLayananURL(gambar: String): String {
        return "$BASE_URL$gambar.png"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }
