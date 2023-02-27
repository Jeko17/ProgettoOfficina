package com.android.officina.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Delete
import com.android.officina.R
import com.android.officina.entities.Cliente

class ClienteAdapter(private val ctx: Context, private val data: List<Cliente>) :
        RecyclerView.Adapter<ClienteAdapter.ViewHolder>(){

    lateinit var onClick: ItemActionListener<Long>
    lateinit var onDelete: ItemActionListener<Long>

            inner class ViewHolder(private val v: View): RecyclerView.ViewHolder(v){
                private val nameView: TextView = v.findViewById(R.id.name)
                private val surnameView: TextView = v.findViewById(R.id.surname)
                private val infoView: Button = v.findViewById(R.id.button_info)
                private val deleteView: Button = v.findViewById(R.id.button_delete)

                fun showCustomers(cliente: Cliente){
                    nameView.text = cliente.name
                    surnameView.text = cliente.surname
                    infoView.setOnClickListener { fireOnClickListener(cliente.id) }
                    deleteView.setOnClickListener{ fireOnDeleteListener(cliente.id) }
                }
            }

    private fun fireOnClickListener(id: Long) {
        if (::onClick.isInitialized) onClick(id)
    }

    private fun fireOnDeleteListener(id: Long){
        if (::onDelete.isInitialized) onDelete(id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.customers_list_item, parent, false))


    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.showCustomers(data[position])
    }

}