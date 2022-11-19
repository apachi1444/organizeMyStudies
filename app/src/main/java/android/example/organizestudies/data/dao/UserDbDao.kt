package android.example.organizestudies.data.dao

import android.example.organizestudies.data.entities.User
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDbDao {
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM User WHERE userId=:key")
    fun getUserByKey(key : Long):User

    @Query("Delete FROM User")
    fun clear()

    @Query("SELECT * FROM User ORDER BY userId DESC")
    fun getAllUsers() : List<User>
}