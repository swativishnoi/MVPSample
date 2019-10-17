package com.codebase.utils

import android.app.AlertDialog
import android.content.Context
import com.codebase.R

class DialogsUtil {

    fun showAlertDialog(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setPositiveButton("Ok") { dialog, which ->
            dialog.dismiss()

        }
        builder.setTitle(context.resources.getString(R.string.app_name))
        builder.setMessage(message)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setCancelable(false)
        builder.create().show()
    }

    fun showAlertDialog(context: Context, title: String, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setPositiveButton("Ok") { dialog, which ->
            dialog.dismiss()

        }
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setCancelable(false)
        builder.create().show()
    }

    fun showAlertDialog(context: Context,positiveButton: String, title: String, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setPositiveButton(positiveButton) { dialog, which ->
            dialog.dismiss()

        }
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setCancelable(false)
        builder.create().show()
    }

    fun showAlertDialog(context: Context,positiveButton: String,negativeButton: String,  title: String, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setPositiveButton(positiveButton) { dialog, which ->
            dialog.dismiss()

        }
        builder.setNegativeButton(negativeButton) { dialog, which ->
            dialog.dismiss()
        }
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setCancelable(false)
        builder.create().show()
    }

}