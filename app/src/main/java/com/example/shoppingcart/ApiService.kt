package com.example.shoppingcart

import com.example.shoppingcart.models.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

  @Headers("Content-type: application/json", "Accept: application/json")
  @GET("products")
  fun getProducts() : Call<List<Product>>
}
