package com.example.myapplicationnextzyroom.fragment.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationnextzyroom.R
import com.example.myapplicationnextzyroom.models.User
import kotlinx.android.synthetic.main.a_user_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.UserViewHolder>() {
    private var userList = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.a_user_row, parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.firstNameTV.text = currentItem.firstName
        holder.itemView.lastNameTV.text = currentItem.lastName
        holder.itemView.ageTV.text = currentItem.age.toString()
        holder.itemView.idTV.text = currentItem.id.toString()
        holder.itemView.aListLayout.setOnClickListener {
            // without argument
            //  holder.itemView.findNavController().navigate(R.id.action_listFragment_to_updateFragment)
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}