package com.example.eldorado_test.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eldorado_test.R
import com.example.eldorado_test.adapters.AdapterPermission
import com.example.eldorado_test.data.Permission
import com.example.eldorado_test.util.helper.PreferencesHelper

class PermissionsFragment : Fragment() {

    var permission : Permission? = null
    private var recyclerViewPermissions : RecyclerView? = null
    private var adapterPermissions: AdapterPermission? = null
    private var arrayListPermissions: ArrayList<Permission>? = null

    private lateinit var preferencesHelper : PreferencesHelper

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {

        arrayListPermissions = ArrayList()
        val view = inflater.inflate(R.layout.fragment_permissions,container,false)

        recyclerViewPermissions = view?.findViewById(R.id.recycler_view_permission)

        preferencesHelper =
            PreferencesHelper(this!!.context!!)
        arrayListPermissions = preferencesHelper.getPermissions()
        Log.d("TAG","PERMISSIONS")

        adapterPermissions = AdapterPermission()
        var mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerViewPermissions!!.layoutManager = mLayoutManager
        recyclerViewPermissions!!.itemAnimator = DefaultItemAnimator()
        recyclerViewPermissions!!.adapter = adapterPermissions

        adapterPermissions!!.add(arrayListPermissions)
        Log.d("TAG","PERMISSIONS")

        return view
    }
}