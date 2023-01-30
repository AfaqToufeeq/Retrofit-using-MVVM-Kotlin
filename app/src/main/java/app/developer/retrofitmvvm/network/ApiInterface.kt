package app.developer.retrofitmvvm.network

import app.developer.retrofitmvvm.model.ApiResponseData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    /**
     * End Point
     * */
    //Get Method
    @GET(value = "/fact")
    fun getData(): Call<ApiResponseData>
}