package app.developer.retrofitmvvm.utils

import android.app.Dialog
import android.app.ProgressDialog.show
import android.content.Context
import android.view.Window
import android.widget.TextView
import androidx.core.content.ContextCompat
import app.developer.retrofitmvvm.R
import app.developer.retrofitmvvm.interfaces.AlertDialogListener

class CommonUtils {
    companion object{

        /**
         * function to show custom dialog
         * @param context activity or fragment context
         * @param title title of dialog
         * @param message message of dialog
         * @param yesBtnTest text for yes button on dialog
         * @param noBtnText text for no button on dialog
         * @param showYesBtnBackground to show yes button background for permission dialog
         * @param alertDialogListener listener for dialog buttons
         * */
        fun showCustomDialog(context: Context, title: String, message: String, yesBtnTest: String, noBtnText: String,
                             showYesBtnBackground: Boolean, alertDialogListener: AlertDialogListener
        ) {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.custom_dialog)
            dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            val titleText: TextView = dialog.findViewById(R.id.title)
            titleText.text = title
            val messageText: TextView = dialog.findViewById(R.id.message)
            messageText.text = message
            val yesBtn: TextView = dialog.findViewById(R.id.tv_OK)
            yesBtn.text = yesBtnTest

            if (showYesBtnBackground){ // to show yes button blue background for permission purpose
                yesBtn.background = ContextCompat.getDrawable(context,R.drawable.btn_background)
                yesBtn.setTextColor(ContextCompat.getColor(context, R.color.white))
            }

            val noBtn: TextView = dialog.findViewById(R.id.tv_cancel)
            noBtn.text = noBtnText

            yesBtn.setOnClickListener {
                alertDialogListener.onPositiveClick()
                dialog.dismiss()
            }
            noBtn.setOnClickListener {
                alertDialogListener.onNegativeClick()
                dialog.dismiss()
            }
//            if(context != null ) {
//                dialog.show() // if fragment use getActivity().isFinishing() or isAdded() method
//            }

        }
    }
}