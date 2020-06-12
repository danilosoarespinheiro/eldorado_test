package com.example.eldorado_test.fragments

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eldorado_test.R
import com.example.eldorado_test.activities.MainActivity
import com.example.eldorado_test.adapters.AdapterSwitch
import com.example.eldorado_test.data.Permission
import com.example.eldorado_test.util.helper.PreferencesHelper

class SwitchFragment : Fragment() {

    var permission : Permission? = null
    var  PERMISSION_ALL = 1
    var  MSG_PERMISSION = ""
    private var recyclerViewPermissions : RecyclerView? = null
    private var adapterPermissions: AdapterSwitch? = null
    private var arrayListPermissions: ArrayList<Permission>? = null

    private lateinit var preferencesHelper : PreferencesHelper

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        arrayListPermissions = ArrayList()
        val view = inflater.inflate(R.layout.fragment_switch,container,false)
        recyclerViewPermissions = view?.findViewById(R.id.recycler_view_switch)

        preferencesHelper = PreferencesHelper(this.context!!)

        arrayListPermissions = preferencesHelper.getPermissions()

        adapterPermissions = AdapterSwitch()
        var mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerViewPermissions!!.layoutManager = mLayoutManager
        recyclerViewPermissions!!.itemAnimator = DefaultItemAnimator()
        recyclerViewPermissions!!.adapter = adapterPermissions

        adapterPermissions!!.add(arrayListPermissions)

        adapterPermissions!!.onItemClick = { it ->
            hasPermission(it.permission)
        }

        return view
    }

    private fun hasPermission(it: String) : Boolean{
        return if (ContextCompat.checkSelfPermission(this.context!!, it) == PackageManager.PERMISSION_GRANTED) {
            true
        } else {
            requestPermissions(arrayOf(it), PERMISSION_ALL)
            false
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        when (requestCode) {
            PERMISSION_ALL -> {
                MSG_PERMISSION = if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    "Access granted to "
                } else {
                    "Access denied to "
                }
                (activity as MainActivity?)?.databaseHandler(MSG_PERMISSION + permissions[0])
                return
            }
        }
    }
}