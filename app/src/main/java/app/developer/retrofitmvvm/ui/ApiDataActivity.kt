package app.developer.retrofitmvvm.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
   lateinit var builder :AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        buttonClick()
    }

    /**
     *sending the request to get respond from api again
     **/
    private fun buttonClick() {
        binding.refreshImageView.setOnClickListener {
            retrofitViewModel.getApiData()
        }

        binding.back.setOnClickListener {
            alertDialog()
        }
    }

    /**
     * Initialzing the View model
     * Calling the method from view model
     * Observing the live data
     */
    @SuppressLint("SetTextI18n")
    private fun initViews() {
        loader = Utils.progressDialog(this@ApiDataActivity)

        if(!loader.isShowing) loader.show()
        retrofitViewModel = ViewModelProvider(this@ApiDataActivity).get(RetrofitViewModel::class.java)

        retrofitViewModel.getApiData()
        retrofitViewModel.dataList.observe(this, Observer {

            Log.d("liveData","${it}\n")
            binding.textView.text= "Fact:\n${it.fact}"
            binding.textView2.text= "Length:\n${it.length}"
        })
        if (loader.isShowing) loader.dismiss()
    }

    fun alertDialog(){
        builder = AlertDialog.Builder(this)
        builder.setTitle("Exit App")
        builder.setMessage("Are you sure, you want to exit app?")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            this@ApiDataActivity.finish()
            this@ApiDataActivity.finishAffinity()
            exitProcess(0)
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            builder.setCancelable(true)
        }
        builder.show()
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            alertDialog()
        }
        return false
        // Disable back button..............
    }

}
