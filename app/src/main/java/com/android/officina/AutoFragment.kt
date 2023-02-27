package com.android.officina

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.android.officina.adapter.AutoAdapter
import com.android.officina.adapter.ClienteAdapter
import com.android.officina.persistence.sql.ClienteDatabase
import java.util.concurrent.Executors

class AutoFragment : Fragment() {

    private lateinit var activityContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activityContext = context as AutoViewActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_auto, container, false)
            v.findViewById<RecyclerView>(R.id.cars_list).apply {
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
                    .getAutoDao()
                Executors.newSingleThreadExecutor().execute {
                adapter = AutoAdapter(activityContext, dao.getAll()).apply {
                    onClick = { id ->
                        startActivity(Intent(activityContext, AutoViewSingoloActivity::class.java).apply {
                            putExtra("id",id)
                        })}
                    onDelete = { id ->
                        Executors.newSingleThreadExecutor().execute{
                            val dao = Room.databaseBuilder(activityContext,ClienteDatabase::class.java,ClienteDatabase.DATABASE_NAME).build().getAutoDao()
                            val auto = dao.infoCars(id)
                            dao.delete(auto)
                        }
                    }
                }
            }
        }
        return v
    }

    companion object {
        @JvmStatic
        fun newInstance() = AutoFragment()
    }
}