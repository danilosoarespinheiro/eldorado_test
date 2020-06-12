package com.example.eldorado_test.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eldorado_test.R
import com.example.eldorado_test.data.Permission
import kotlinx.android.synthetic.main.recycler_view_permission_row.view.*
import kotlinx.android.synthetic.main.recycler_view_switch_row.view.*
import kotlinx.android.synthetic.main.recycler_view_switch_row.view.tv_permission

class PermissionViewHolder(val view: View): RecyclerView.ViewHolder(view){

    fun bindView(item: Permission, position: Int){
        itemView.tv_permission.text = item.permission

        if(item.status){
            itemView.tv_status.text= "enabled"
        }
    }
}

class AdapterPermission(val data: MutableList<Permission> = mutableListOf()):   RecyclerView.Adapter<PermissionViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PermissionViewHolder {
        var view =  LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_permission_row, parent, false)
        return PermissionViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PermissionViewHolder, position: Int) =
        holder.bindView(data[position], position)

    fun add(itens: List<Permission>?){
        data.clear()
        itens?.let { data.addAll(it) }
        notifyDataSetChanged()
    }

}