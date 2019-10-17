package com.codebase.modules.mainModule

import android.os.Bundle
import com.codebase.R
import com.codebase.base.BaseActivity
import com.codebase.base.JoinTrainApplication
import com.codebase.modules.mainModule.homeScreen.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as JoinTrainApplication).getAppComponent().inject(this)
        setContentView(R.layout.activity_main)
        mPrefs.setDeviceToken("abcd")
        hello.setText("jsahjkdhajkhkajf")
        HomeActivity::class.start(this,true)
    }

    override fun onSuccess(response: Any) {

    }

    override fun enableButton() {

    }

    override fun disableButton() {

    }

}
