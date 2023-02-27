package com.android.officina

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.room.Room
import androidx.room.migration.Migration
import com.android.officina.adapter.AutoAdapter
import com.android.officina.adapter.InterventiAdapter
import com.android.officina.persistence.sql.ClienteDatabase
import java.util.concurrent.Executors

class InterventiFragment : Fragment() {

    private lateinit var activityContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activityContext = context as InterventiViewActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_interventi, container, false)
        v.findViewById<RecyclerView>(R.id.interventi_list).apply {
            layoutManager = LinearLayoutManager(activityContext).apply {
                orientation = RecyclerView.VERTICAL
            }
            val dao = Room
                .databaseBuilder(
                activityContext,
                ClienteDatabase::class.java,
                ClienteDatabase.DATABASE_NAME
            )
                .build()
                .getInterventiDao()
            Executors.newSingleThreadExecutor().execute {
                adapter = InterventiAdapter(activityContext, dao.getAll()).apply {
                        onClick = { id ->
                            startActivity(
                                Intent(
                                    activityContext,
                                    InterventiViewSingoloActivity::class.java
                                ).apply {
                                    putExtra("id", id)
                                })
                        }
                    onDelete = { id ->
                        Executors.newSingleThreadExecutor().execute{
                            val intervento = dao.infoInterventi(id)
                            dao.delete(intervento)
                        }
                        Toast.makeText(activityContext,"Ha cancellato l'intervento con id $id", Toast.LENGTH_LONG).show()
                    }
                    }
            }
        }
        return v
    }

    companion object {

        @JvmStatic
        fun newInstance() = InterventiFragment()
    }
}