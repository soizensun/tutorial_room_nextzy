package com.example.myapplicationnextzyroom.fragment.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplicationnextzyroom.R
import com.example.myapplicationnextzyroom.models.User
import com.example.myapplicationnextzyroom.viewModels.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.android.synthetic.main.fragment_update.view.firstNameEditET

class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.firstNameEditET.setText(args.currentUser.firstName)
        view.lastNameEditET.setText(args.currentUser.lastName)
        view.ageEditET.setText(args.currentUser.age.toString())

        view.editUserBTN.setOnClickListener {
            updateUser()
        }

        // add menu
        setHasOptionsMenu(true)

        // fix keyboard bug in fragment
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        return view
    }

    private fun updateUser(){
        val firstName = firstNameEditET.text.toString()
        val lastName = lastNameEditET.text.toString()
        val age = ageEditET.text.toString().toInt()
        if(inputCheck(firstName, lastName, ageEditET.text)){
            val updateUser = User(args.currentUser.id, firstName, lastName, age)
            userViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "update success",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        else Toast.makeText(requireContext(), "fail success",Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.deleteIcon){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){_,_ ->
            userViewModel.deleteUser(args.currentUser)
        }
        builder.setNegativeButton("no"){_,_ ->

        }
        builder.setTitle("Delete ${args.currentUser.firstName} ${args.currentUser.lastName}?")
        builder.setMessage("Are you sure to delete ${args.currentUser.firstName} ${args.currentUser.lastName} ?")
        builder.create().show()
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        Log.i("age", "inputCheck: $age")
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}