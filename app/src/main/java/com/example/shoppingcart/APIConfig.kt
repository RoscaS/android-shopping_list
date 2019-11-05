package com.example.shoppingcart

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object APIConfig {
  private var retrofit: Retrofit? = null

  val BASE_URL = "https://my-json-server.typicode.com/RoscaS/fake-api/"
  var gson = GsonBuilder().setLenient().create()

  fun getRetrofitClient(context: Context): Retrofit {
    val okHttpClient = OkHttpClient.Builder().build()

    if (retrofit == null) {
      retrofit = Retrofit.Builder()
              .baseUrl(BASE_URL)
              .client(okHttpClient)
              .addConverterFactory(ScalarsConverterFactory.create())
              .addConverterFactory(GsonConverterFactory.create(gson))
              .build()
    }
    return retrofit!!
  }
}