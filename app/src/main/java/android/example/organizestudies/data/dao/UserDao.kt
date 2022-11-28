package android.example.organizestudies.data.dao

import android.example.organizestudies.data.entities.User
import android.example.organizestudies.data.entities.relations.UserModuleCrossRef
import android.example.organizestudies.data.entities.relations.UserWithModules
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Insert
    fun insert(userModuleCrossRef: UserModuleCrossRef)

    @Query("SELECT * FROM User WHERE username=:username AND password =:password LIMIT 1")
    fun getUserByUsernameAndPassword(username: String, password: String): User

    @Query("Delete FROM User")
    fun clear()

    @Query("SELECT * FROM User ORDER BY username DESC")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM User WHERE username =:username LIMIT 1")
    fun getUserByUsername(username: String): User

    @Transaction
    @Query("SELECT * FROM User WHERE username=:userName")
    fun getUsersWithModules(userName: String): LiveData<List<UserWithModules>>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertModule(module: Module)
//
//    @Transaction
//    @Query("SELECT * FROM User where userId=:userId")
//    suspend fun getUserWithModules(userId: String): List<UserWithModules>
}