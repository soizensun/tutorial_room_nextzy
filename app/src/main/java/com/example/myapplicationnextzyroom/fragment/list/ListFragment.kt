package com.example.myapplicationnextzyroom.fragment.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationnextzyroom.R
import com.example.myapplicationnextzyroom.fragment.add.SwipeToDelete
import com.example.myapplicationnextzyroom.models.User
import com.example.myapplicationnextzyroom.viewModels.UserViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.a_user_row.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel
//    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val adapter = ListAdapter()
        val recyclerView = view.userRecycleList
        val item = object : SwipeToDelete(requireContext(), 0, ItemTouchHelper.LEFT){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val builder = AlertDialog.Builder(requireContext())
                builder.setPositiveButton("yes") { _, _ ->
                    adapter.deleteUser(viewHolder.adapterPosition)
                    userViewModel.deleteUser(User(
                        viewHolder.itemView.idTV.text.toString().toInt(),
                        viewHolder.itemView.firstNameTV.text.toString(),
                        viewHolder.itemView.lastNameTV.text.toString(),
                        viewHolder.itemView.ageTV.text.toString().toInt()
                    ))
                }
                builder.setNegativeButton("no") { _, _ ->
//                    Snackbar.make(requireView(), "Hello", Snackbar.LENGTH_LONG).show()

                }
                builder.setTitle("Delete this User?")
                builder.setMessage("Are you sure to delete ?")
                builder.create().show()

            }
        }
        val itemTouchHelper = ItemTouchHelper(item)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        userViewModel.readAllUser.observe(viewLifecycleOwner, Observer {user ->
            adapter.setData(ArrayList(user))
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        // add menu
        setHasOptionsMenu(true)

        view.addUserfloatingButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.deleteIcon){
            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){_,_ ->
            userViewModel.deleteAllUser()
        }
        builder.setNegativeButton("no"){_,_ ->

        }
        builder.setTitle("Delete all user.")
        builder.setMessage("Are you sure to delete all user ?")
        builder.create().show()
    }


}