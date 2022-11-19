package android.example.organizestudies.data.dao

import android.content.Context
import android.example.organizestudies.data.entities.User
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 5, exportSchema = false)
abstract class UserDb : RoomDatabase() {
    abstract fun userDbDao(): UserDbDao

    companion object {
        @Volatile
        private var INSTANCE: UserDb? = null

        fun getInstance(context: Context): UserDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDb::class.java,
                        "testDB"
                    )
                        // we have to create a migration object with a migration object when
                        // we create our db !
                        // migration means if we change the db schema
                        // for example by changing the number of cols
                        // we need a way to convert the existing tables into the new schema
                        // immigration object helps you convert all the row of the old schema
                        // and convert them into the new schema
                        // thus if a user upgrades from one version of the app
                        // with one db schema to another schema of the new app we will not lost the informations
                        // but when we use the fallBackToDestructionMigration ( we
                        // will destruct and rebuild the db in the case of upgrading the db )
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}