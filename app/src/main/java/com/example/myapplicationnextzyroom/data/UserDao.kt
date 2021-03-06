package com.example.myapplicationnextzyroom.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplicationnextzyroom.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("DELETE FROM user_table")
    fun deleteAllUser()
}