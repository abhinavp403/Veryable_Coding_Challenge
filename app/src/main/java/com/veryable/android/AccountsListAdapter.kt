package com.veryable.android

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AccountsListAdapter(private val context: Context, private val accountList: List<Account>) : RecyclerView.Adapter<AccountsListAdapter.ViewHolder>() {

    inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.account_name)
        var type: TextView = itemView.findViewById(R.id.account_type)
        var desc: TextView = itemView.findViewById(R.id.account_desc)
        var accounticon: ImageView = itemView.findViewById(R.id.account_icon)

        // Goes to PayoutsDetailActivity on item click
        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val intent = Intent(context, PayoutsDetailActivity::class.java)
                    intent.putExtra("name", accountList[adapterPosition].name)
                    intent.putExtra("desc", accountList[adapterPosition].desc)
                    intent.putExtra("type", accountList[adapterPosition].type)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.accounts_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return accountList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Displays view for each item
        holder.name.text = accountList[position].name
        holder.desc.text = accountList[position].desc
        if (accountList[position].type == "card") {
            holder.type.text = context.getString(R.string.card)
            Glide.with(context).load(R.drawable.baseline_credit_card_black_48pt_1x)
                .into(holder.accounticon)
        } else {
            holder.type.text = context.getString(R.string.bank)
            Glide.with(context).load(R.drawable.baseline_account_balance_black_48pt_1x)
                .into(holder.accounticon)
        }
    }
}