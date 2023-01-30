package app.developer.retrofitmvvm.ui

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.developer.retrofitmvvm.R
import app.developer.retrofitmvvm.databinding.ActivityApiBinding
import app.developer.retrofitmvvm.extension.Utils
import app.developer.retrofitmvvm.interfaces.AlertDialogListener
import app.developer.retrofitmvvm.utils.CommonUtils
import app.developer.retrofitmvvm.viewmodel.RetrofitViewModel
import kotlin.system.exitProcess

class ApiDataActivity : AppCompatActivity() {
    lateinit var binding: ActivityApiBinding
    lateinit var retrofitViewModel: RetrofitViewModel
    lateinit var loader: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        buttonClick()
    }

    /**
     *sending the get Respond again
     **/
    private fun buttonClick() {
        binding.refreshImageView.setOnClickListener {
            retrofitViewModel.getApiData()
        }
    }

    /**
     * Initialzing the View model
     * Calling the method from view model
     * Observing the live data
     */
    private fun initViews() {
        loader = Utils.progressDialog(this@ApiDataActivity)

        if(!loader.isShowing) loader.show()
        retrofitViewModel = ViewModelProvider(this@ApiDataActivity).get(RetrofitViewModel::class.java)

        retrofitViewModel.getApiData()
        retrofitViewModel.dataList.observe(this, Observer {

            Log.d("liveData","${it.fact}\n")
            binding.textView.text= it.fact
        })
        if (loader.isShowing) loader.dismiss()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        CommonUtils.showCustomDialog(this@ApiDataActivity,
            "Exit App?",
            "Are you sure you want to exit the app?",
            "Yes",
            "Cancel",
            false,
            object: AlertDialogListener {
                override fun onPositiveClick() {
                    this@ApiDataActivity.finish()
                    this@ApiDataActivity.finishAffinity()
                    exitProcess(0)
                }

                override fun onNegativeClick() {
                }

            })
    }
}