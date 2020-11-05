package com.example.myapplicationnextzyroom.repositories

import androidx.lifecycle.LiveData
import com.example.myapplicationnextzyroom.data.UserDao
import com.example.myapplicationnextzyroom.models.User

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }
}