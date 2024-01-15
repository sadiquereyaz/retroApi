package `in`.instea.apiretrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("products")        // "products" is the end point (end part of URL) of the api
    fun getProductData(): Call<MyData>
}