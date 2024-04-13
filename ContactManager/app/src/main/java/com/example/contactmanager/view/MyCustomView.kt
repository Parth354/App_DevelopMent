package com.example.contactmanager.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactmanager.R
import com.example.contactmanager.Room.Contacts
import com.example.contactmanager.databinding.CardViewBinding

class MyCustomView(private val contactsList:List<Contacts>,
    private val clickListener: (Contacts)-> Unit
    ):RecyclerView.Adapter<MyCustomView.MyViewHolder> (){

        class MyViewHolder(val binding: CardViewBinding):RecyclerView.ViewHolder(binding.root){

            fun bind(contacts:Contacts,clickListener: (Contacts) -> Unit){
                binding.nameTextView.text=contacts.name
                binding.emailTextView.text=contacts.email
                binding.listLinearLayout.setOnClickListener{
                    clickListener(contacts)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding:CardViewBinding=DataBindingUtil.inflate(layoutInflater, R.layout.card_view,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(contactsList[position],clickListener)
    }
}