package com.android.officina.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.officina.R
import com.android.officina.entities.Interventi

class InterventiAdapter(private val ctx: Context, private val data: List<Interventi>) :
    RecyclerView.Adapter<InterventiAdapter.ViewHolder>() {

    lateinit var onClick: ItemActionListener<Long>
    lateinit var onDelete: ItemActionListener<Long>

    inner class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        private val dataStartView: TextView = v.findViewById(R.id.dataStart)
        private val dataFinishView: TextView = v.findViewById(R.id.dataFinish)
        private val targaView: TextView = v.findViewById(R.id.targa)
        private val infoView: Button = v.findViewById(R.id.button_info)
        private val deleteView: Button = v.findViewById(R.id.button_delete)

        fun showInterventi(interventi: Interventi) {
            dataStartView.text = interventi.dateStart
            dataFinishView.text = interventi.dateFinish
            targaView.text = interventi.car.toString()
            infoView.setOnClickListener {fireOnClickListener(interventi.id)}
            deleteView.setOnClickListener {fireOnDeleteListener(interventi.id)}
        }
    }

    private fun fireOnClickListener(id: Long) {
        if (::onClick.isInitialized) onClick(id)
    }

    private fun fireOnDeleteListener(id: Long) {
        if (::onClick.isInitialized) onClick(id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.interventi_list_item, parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.showInterventi(data[position])
    }
}