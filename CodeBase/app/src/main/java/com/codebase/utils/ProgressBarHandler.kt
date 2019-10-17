package com.codebase.utils

import android.app.Activity
import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.codebase.R

class ProgressBarHandler {

    private var mProgressBar: ProgressBar? = null
    lateinit var rl: RelativeLayout


    fun setContext(context: Context) {
        val layout = (context as Activity).findViewById<View>(android.R.id.content).rootView as ViewGroup

        mProgressBar = ProgressBar(context, null)
        mProgressBar!!.isIndeterminate = true
        mProgressBar!!.indeterminateDrawable.setColorFilter(
            ContextCompat.getColor(context, R.color.colorPrimary),
            android.graphics.PorterDuff.Mode.MULTIPLY
        )

        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )

        rl = RelativeLayout(context)

        rl.gravity = Gravity.CENTER
        rl.addView(mProgressBar)
        rl.setOnClickListener { }
        layout.addView(rl, params)

        hideProgress()
    }

    fun showProgress() {
        rl.isClickable = true
        mProgressBar!!.visibility = View.VISIBLE
    }

    fun hideProgress() {
        rl.isClickable = false
        mProgressBar!!.visibility = View.INVISIBLE
    }
}