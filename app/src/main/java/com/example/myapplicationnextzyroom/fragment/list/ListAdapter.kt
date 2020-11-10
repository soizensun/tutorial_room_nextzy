package com.example.myapplicationnextzyroom.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationnextzyroom.R
import com.example.myapplicationnextzyroom.models.User
import com.example.myapplicationnextzyroom.viewModels.UserViewModel
import kotlinx.android.synthetic.main.a_user_row.view.*

class ListAdapter(): RecyclerView.Adapter<ListAdapter.UserViewHolder>() {
    private var userList = arrayListOf<User>()
//    private var removedPosition: Int = 0
//    private lateinit var removedItem: User

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.a_user_row, parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
//        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
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

    fun setData(user: ArrayList<User>){
        this.userList = user
        notifyDataSetChanged()
    }

    fun deleteUser(position: Int){
//        removedPosition = position
//        removedItem = userList[position]

        userList.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        private var idTV = R.id.idTV
    }
}