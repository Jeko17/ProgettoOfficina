package com.android.officina.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.officina.R
import com.android.officina.entities.Auto

class AutoAdapter(private val ctx: Context, private val data: List<Auto>) :
        RecyclerView.Adapter<AutoAdapter.ViewHolder>(){

    lateinit var onClick: ItemActionListener<Long>
    lateinit var onDelete: ItemActionListener<Long>

            inner class ViewHolder(private val v: View): RecyclerView.ViewHolder(v){
                private val targaView: TextView = v.findViewById(R.id.targa)
                private val modelloView: TextView = v.findViewById(R.id.model)
                private val infoView: Button = v.findViewById(R.id.button_info)
                private val deleteView: Button = v.findViewById(R.id.button_delete)

                fun showAuto(auto: Auto){
                    targaView.text = auto.targa
                    modelloView.text = auto.model
                    infoView.setOnClickListener { fireOnClickListener(auto.id) }
                    deleteView.setOnClickListener { fireOnDeleteListener(auto.id) }
                }

            }

    private fun fireOnClickListener(id: Long) {
        if (::onClick.isInitialized) onClick(id)
    }

    private fun fireOnDeleteListener(id: Long) {
        if (::onDelete.isInitialized) onDelete(id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.auto_list_item, parent, false))


    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.showAuto(data[position])
    }
}