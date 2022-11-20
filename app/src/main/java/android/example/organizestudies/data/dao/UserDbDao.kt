package android.example.organizestudies.data.dao

import android.example.organizestudies.data.entities.Module
import android.example.organizestudies.data.entities.User
import android.example.organizestudies.data.entities.UserWithModules
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
    fun getUserByKey(key: Long): User

    @Query("SELECT * FROM User WHERE username=:username AND password =:password LIMIT 1")
    fun getUserByUsernameAndPassword(username: String, password: String): User

    @Query("Delete FROM User")
    fun clear()

    @Query("SELECT * FROM User ORDER BY userId DESC")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM User WHERE username =:username LIMIT 1")
    fun getUserByUsername(username: String): User

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertModule(module: Module)
//
//    @Transaction
//    @Query("SELECT * FROM User where userId=:userId")
//    suspend fun getUserWithModules(userId: String): List<UserWithModules>
}