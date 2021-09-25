package com.veryable.android

import retrofit2.Response
import retrofit2.http.GET

interface Service {

    @GET("/veryable.json")
    suspend fun getAccounts(): Response<List<Account>>
}