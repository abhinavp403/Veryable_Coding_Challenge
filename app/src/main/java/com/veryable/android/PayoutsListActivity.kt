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

class PayoutsListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPayoutsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payouts_list)

        // Get Retrofit instance which is created in Service class
        val service = Service.getInstance()

        // Using coroutine to retrieve api response
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getAccounts()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val items = response.body()

                    // Calling recyclerview adapter with list of accounts retrieved from response
                    binding.recyclerview.apply {
                        layoutManager = LinearLayoutManager(this@PayoutsListActivity)
                        adapter = AccountsListAdapter(this@PayoutsListActivity, items!!)
                    }
                } else {
                    Log.e("Error Fetching Data", response.code().toString())
                }
            }
        }
    }
}