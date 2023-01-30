package app.developer.retrofitmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.developer.retrofitmvvm.R
import app.developer.retrofitmvvm.databinding.ActivityApiBinding
import app.developer.retrofitmvvm.viewmodel.RetrofitViewModel

class ApiDataActivity : AppCompatActivity() {
    lateinit var binding: ActivityApiBinding
    lateinit var retrofitViewModel: RetrofitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    /**
     * Initialzing the View model
     * Calling the method from view model
     * Observing the live data
     */
    private fun initViews() {

        retrofitViewModel = ViewModelProvider(this@ApiDataActivity).get(RetrofitViewModel::class.java)

        retrofitViewModel.getApiData()
        retrofitViewModel.dataList.observe(this, Observer {

            Log.d("liveData","${it.fact}\n")
        })


    }
}