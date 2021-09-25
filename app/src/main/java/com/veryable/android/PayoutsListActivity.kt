package com.veryable.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.veryable.android.databinding.ActivityPayoutsListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PayoutsListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPayoutsListBinding
    private var baseURL = "https://veryable-public-assets.s3.us-east-2.amazonaws.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payouts_list)

        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(Service::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getAccounts()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val items = response.body()
                    if (items != null) {
                        binding.recyclerview.apply {
                            layoutManager = LinearLayoutManager(this@PayoutsListActivity)
                            adapter = AccountsListAdapter(this@PayoutsListActivity, items)
                        }
                    }
                } else {
                    Log.e("Error Fetching Data", response.code().toString())
                }
            }
        }
    }
}