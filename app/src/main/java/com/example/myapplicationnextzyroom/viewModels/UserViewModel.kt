package com.example.myapplicationnextzyroom.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplicationnextzyroom.data.UserDatabase
import com.example.myapplicationnextzyroom.repositories.UserRepository
import com.example.myapplicationnextzyroom.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application){

    var readAllUser: LiveData<List<User>>
    private var repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(
            application
        ).userDao()
        repository =
            UserRepository(
                userDao
            )
        readAllUser = repository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }
}