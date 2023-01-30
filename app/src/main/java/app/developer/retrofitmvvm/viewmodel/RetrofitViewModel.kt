package app.developer.retrofitmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.developer.retrofitmvvm.model.ApiResponseData
import app.developer.retrofitmvvm.network.RetrofitNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitViewModel:ViewModel() {

    /**
     * MutableList
     * Live Data to observe the data
     */

    var dataList = MutableLiveData<ApiResponseData>()


    /**
     * Funtion to fetch the data from the API
     */
    fun getApiData(){

        val userService = RetrofitNetwork.retrofitData

        /**
         * Get the response from the api
         * @exception t throws the error
         * onSuccess add the data in live data variable
         */
        userService.clone().enqueue(object : Callback<ApiResponseData> {
            override fun onResponse(
                call: Call<ApiResponseData>,
                response: Response<ApiResponseData>
            ) {
                dataList.value= response.body()
            }

            override fun onFailure(call: Call<ApiResponseData>, t: Throwable) {
               Log.d("RetrofitViewModel","${t.message}")
            }
        })
    }
}