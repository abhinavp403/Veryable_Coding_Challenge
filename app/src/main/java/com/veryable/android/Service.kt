package com.veryable.android

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Service {

    @GET("/veryable.json")
    suspend fun getAccounts(): Response<List<Account>>

    companion object {
        var service: Service? = null
        val BASE_URL : String = "https://veryable-public-assets.s3.us-east-2.amazonaws.com"

        fun getInstance() : Service {
            if(service == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                service = retrofit.create(Service::class.java)
            }
            return service!!
        }
    }
}