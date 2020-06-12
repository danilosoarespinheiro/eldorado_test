package com.example.eldorado_test.activities

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.eldorado_test.R
import com.example.eldorado_test.data.AppDatabase
import com.example.eldorado_test.data.Permission
import com.example.eldorado_test.data.dao.LogDao
import com.example.eldorado_test.data.entities.LogEntity
import com.example.eldorado_test.fragments.PermissionsFragment
import com.example.eldorado_test.fragments.SwitchFragment
import com.example.eldorado_test.util.helper.PreferencesHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var db: AppDatabase? = null
    private var logDao: LogDao? = null

    private val switchFragment = SwitchFragment()
    private val permissionsFragment = PermissionsFragment()

    var mCount = 0
    private var list: ArrayList<Permission>? = null

    private lateinit var preferencesHelper : PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationListener()
        fragmentSwitch()

        databaseHandler("Started App")
    }

    fun databaseHandler(action: String) {
        Observable.fromCallable {
            db = AppDatabase.getAppDataBase(context = this)

            logDao = db?.logDao()

            val log = LogEntity(action = action)

            with(logDao) {
                this?.insertLog(log)
            }
            db?.logDao()?.getLog()

        }.doOnNext { list ->
            var finalString = ""
            list?.map {
                Log.d("RESULT", it.action)
            }

        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    private fun fragmentSwitch() {
        getPermissions()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_container, switchFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun fragmentPermission() {
        getPermissions()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_container, permissionsFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun bottomNavigationListener() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_switch -> {
                    databaseHandler("Started FragmentSwitch")
                    fragmentSwitch()
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.action_list -> {
                    databaseHandler("Started FragmentPermission")
                    fragmentPermission()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    override fun onBackPressed() {
        when {
            switchFragment.isVisible -> {
                finish()
            }
            permissionsFragment.isVisible -> {
                finish()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }

    private fun getPermissions() {
        list = ArrayList()
        val info: PackageInfo = this.packageManager?.getPackageInfo(
            this.packageName,
            PackageManager.GET_PERMISSIONS
        )!!
        val permissions = info.requestedPermissions

        permissions.forEach {
            val permission = Permission()
            permission.id = mCount
            permission.permission = it
            permission.status = hasPermission(it)
            list?.add(permission)
            mCount ++

        }

        preferencesHelper =
            PreferencesHelper(this)
        preferencesHelper.savePermissions(list)
    }

    private fun hasPermission(it: String) : Boolean{
        return if (ContextCompat.checkSelfPermission(this, it)
            == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
            Log.d("TAG", "PERMITED_1")
            true
        } else {
            Log.d("TAG", "NOT_PERMITED_1")
            false
        }
    }
}
