package com.android.officina

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.android.officina.adapter.ClienteAdapter
import com.android.officina.persistence.sql.ClienteDatabase
import java.util.concurrent.Executors

class ClienteFragment : Fragment() {

    private lateinit var activityContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activityContext = context as ClientiViewActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_cliente, container, false)
        v.findViewById<RecyclerView>(R.id.customers_list).apply {
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
                .getClienteDao()
            Executors.newSingleThreadExecutor().execute {
                adapter = ClienteAdapter(activityContext, dao.getAll()).apply {
                    onClick = { id ->
                        startActivity(
                            Intent(
                                activityContext,
                                ClienteViewSingoloActivity::class.java
                            ).apply {
                                putExtra("id", id)
                            })
                    }
                    onDelete = { id ->
                        Executors.newSingleThreadExecutor().execute{
                            val cliente = dao.infoCustomers(id)
                            dao.delete(cliente)
                        }
                        Toast.makeText(activityContext,"Ha cancellato il cliente con $id", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        return v
    }

    companion object {
        @JvmStatic
        fun newInstance() = ClienteFragment()
    }
}