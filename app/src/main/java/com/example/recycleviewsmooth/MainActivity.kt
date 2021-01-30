package com.example.recycleviewsmooth

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.recycleviewsmooth.model.Respone
import com.example.recycleviewsmooth.recycle.RecycleAdapter
import com.example.recycleviewsmooth.retrofit.ApiService
import com.example.recycleviewsmooth.retrofit.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var snapHelper: LinearSnapHelper
    private lateinit var adapter: RecycleAdapter
    private lateinit var btnAdd: Button
    private var listItem: ArrayList<String> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private var apiService: ApiService? = null
    private lateinit var process: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rcv_item)
        btnAdd = findViewById(R.id.btn_add)
        process = findViewById(R.id.prb)
        apiService = ApiUtils.getApi()
//        addList()
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        snapHelper = LinearSnapHelper()
        adapter = RecycleAdapter(listItem)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        snapHelper.attachToRecyclerView(recyclerView)
        btnAdd.setOnClickListener {
            process.visibility = View.VISIBLE
            apiService!!.getData()!!.enqueue(object : Callback<Respone?> {
                override fun onFailure(call: Call<Respone?>, t: Throwable) {
                    Log.d("TAG112", "onFailure:  ")
                }

                override fun onResponse(call: Call<Respone?>, response: Response<Respone?>) {
                    if (response.code() == 200) {
                        listItem.add(response.body()!!.message)
                        adapter.notifyDataSetChanged()
                    }
                }

            })
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                process.visibility = View.GONE
            }, 1000)
        }
    }
}