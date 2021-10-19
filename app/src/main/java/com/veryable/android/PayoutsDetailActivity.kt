package com.veryable.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.veryable.android.databinding.ActivityPayoutsDetailBinding

class PayoutsDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPayoutsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payouts_detail)

        // Go back to PayoutsListActivity
        binding.toolbarTopDetail.setNavigationOnClickListener {
            val intent = Intent(this@PayoutsDetailActivity, PayoutsListActivity::class.java)
            startActivity(intent)
        }

        // Go back to PayoutsListActivity
        binding.doneButton.setOnClickListener {
            val intent = Intent(this@PayoutsDetailActivity, PayoutsListActivity::class.java)
            startActivity(intent)
        }

        // Receives intent data
        val intent = intent
        val name = intent.extras!!.getString("name")
        val desc = intent.extras!!.getString("desc")
        val type = intent.extras!!.getString("type")

        // Displays intent data
        binding.accountNameDetail.text = name
        binding.accountDescDetail.text = desc
        if (type == "card") {
            Glide.with(this).load(R.drawable.baseline_credit_card_black_48pt_1x)
                .into(binding.detailIcon)
        } else {
            Glide.with(this).load(R.drawable.baseline_account_balance_black_48pt_1x)
                .into(binding.detailIcon)
        }
    }
}