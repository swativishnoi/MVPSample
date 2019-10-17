package com.codebase.modules.mainModule.homeScreen

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.codebase.R
import com.codebase.base.BaseActivity
import com.codebase.base.JoinTrainApplication
import com.codebase.modules.userAccountModule.signUpScreen.SignUpActivity
import com.codebase.utils.PreferenceManager
import javax.inject.Inject


class HomeActivity : BaseActivity(), HomeView, NavigationView.OnNavigationItemSelectedListener,
                    BottomNavigationView.OnNavigationItemSelectedListener
{
    @Inject
    lateinit var mPresenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as JoinTrainApplication).getAppComponent().inject(this)
        setContentView(R.layout.activity_home)
        mProgressHandler.setContext(this@HomeActivity)
        mPresenter.injectDependencies(this)
        mPresenter.attachView(this)
        mPrefs = PreferenceManager(this@HomeActivity)
        setUpToolBar()
    }

    fun setUpToolBar(){
        val toolbar: Toolbar = findViewById(R.id.apptoolbar)
        val mTitle = toolbar.findViewById(R.id.toolbar_title) as TextView
        setSupportActionBar(toolbar)
        mTitle.text = toolbar.title
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onSuccess(response: Any) {
    }

    override fun enableButton() {
    }

    override fun disableButton() {
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d("ItemSelected",item.title.toString() )
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_blogs -> {
                val blogFragment = BlogFragment()
                val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
                if (currentFragment is BlogFragment) {
                } else {
                    setFragment(blogFragment, "blogFragment")
                }
            }

            R.id.nav_faq -> {
                SignUpActivity::class.start(this,true)
            }
            R.id.nav_terms -> {

            }
            R.id.nav_about_us -> {

            }
            R.id.nav_privacy -> {

            }
            R.id.nav_contact_us -> {

            }
            R.id.nav_signout -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    fun setFragment(fragment: Fragment, fName:String) {
        if (supportFragmentManager.backStackEntryCount > 0) {
            val done = supportFragmentManager.popBackStackImmediate()
        }else{

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.commit()
        }
    }

    public fun addFragment(fragment: Fragment, fName:String) {

        if (supportFragmentManager.backStackEntryCount > 0) {
            val done = supportFragmentManager.popBackStackImmediate()
        }

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, fragment)
        transaction.addToBackStack(fName)
        transaction.commit()
    }


    fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}
