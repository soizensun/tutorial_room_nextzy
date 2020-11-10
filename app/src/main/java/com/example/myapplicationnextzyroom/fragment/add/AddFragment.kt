package com.example.myapplicationnextzyroom.fragment.add

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplicationnextzyroom.R
import com.example.myapplicationnextzyroom.models.User
import com.example.myapplicationnextzyroom.viewModels.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_add, container, false)

        // bind viewModel and view
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.addUserBTN.setOnClickListener {
            insertDataToDataBase()
        }
        // fix keyboard bug in fragment
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        return view
    }

    private fun insertDataToDataBase() {
        val firstName = firstNameET.text.toString()
        val lastName = lastNameET.text.toString()
        val age = ageET.text.toString()
        if(inputCheck(firstName, lastName, age)){
            val user = User(
                0,
                firstName,
                lastName,
                Integer.parseInt(age)
            )
            userViewModel.addUser(user)
            Toast.makeText(activity, "add User success", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else Toast.makeText(activity, "please fill all of fields.", Toast.LENGTH_SHORT).show()

    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {
        return !(firstName == "" || lastName == "" || age == "")
    }

}