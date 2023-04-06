package com.basgroup.turboservicebarcode.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitFactory {
    val BASE_URL=""

    var logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    var okHttpClient = OkHttpClient.Builder()
        /*.authenticator { route: Route?, response: Response ->
            val request: Request = response.request()
            if (request.header("Authorization") != null)
                return@authenticator null
            request.newBuilder()
                //.header("Authorization", Credentials.basic("username", "password"))
                .build()
        }*/
        .addInterceptor(logging)
        .build()

    fun makeRetrofitService(): RetrofitService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(RetrofitService::class.java)
    }
}