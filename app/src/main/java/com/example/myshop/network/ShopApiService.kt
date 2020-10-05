package com.example.myshop.network

import androidx.lifecycle.MutableLiveData
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.*

private const val BASE_URL = "http://shop28663.horoshop.ua/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

interface ShopApiService{
    @FormUrlEncoded
    @POST("api/auth/")
    fun userLogin(
        @Field("login") login:String,
        @Field ("password") password: String
    ) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("api/catalog/export/")
    fun catalogExport(
        @Field("token") token: String
    ) : Call<ResponseBody>

    companion object{
        operator fun invoke(): ShopApiService{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(ShopApiService::class.java)
        }
    }
}

