package com.example.eldorado_test.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eldorado_test.R
import com.example.eldorado_test.data.Permission
import kotlinx.android.synthetic.main.recycler_view_switch_row.view.*

class SwitchViewHolder(val view: View, private val onItemClick: ((Permission) -> Unit)?): RecyclerView.ViewHolder(view){

    fun bindView(item: Permission, position: Int){
        itemView.tv_permission.text = item.permission
        itemView.switch_permission.setOnClickListener {
            onItemClick?.invoke(item)
        }

        itemView.switch_permission.isChecked = item.status == true
    }
}

class AdapterSwitch(val data: MutableList<Permission> = mutableListOf()):   RecyclerView.Adapter<SwitchViewHolder>(){

    var onItemClick: ((Permission) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwitchViewHolder {
        var view =  LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_switch_row, parent, false)
        return SwitchViewHolder(
            view,
            onItemClick
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: SwitchViewHolder, position: Int) =
        holder.bindView(data[position], position)

    fun add(itens: List<Permission>?){
        data.clear()
        itens?.let { data.addAll(it) }
        notifyDataSetChanged()
    }

}