package android.example.organizestudies.data.db

import android.content.Context
import android.example.organizestudies.data.dao.FileDao
import android.example.organizestudies.data.dao.ModuleDao
import android.example.organizestudies.data.dao.UserDao
import android.example.organizestudies.data.dao.UserModuleCrossRefDao
import android.example.organizestudies.data.entities.File
import android.example.organizestudies.data.entities.Module
import android.example.organizestudies.data.entities.User
import android.example.organizestudies.data.entities.relations.UserModuleCrossRef
import android.example.organizestudies.utils.consts.ConstNumbers
import android.example.organizestudies.utils.Converters
import android.os.AsyncTask
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(
    entities = [User::class, File::class, Module::class, UserModuleCrossRef::class],
    version = ConstNumbers.CURRENT_VERSION_DB,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class UserDb : RoomDatabase() {

    abstract fun userDbDao(): UserDao
    abstract fun moduleDao(): ModuleDao
    abstract fun fileDao(): FileDao
    abstract fun userModuleCrossRefDao(): UserModuleCrossRefDao


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
                        .addCallback(roomCallback)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }

        private val roomCallback: Callback = object : Callback() {
            override fun onCreate(@NonNull db: SupportSQLiteDatabase) {
                super.onCreate(db)
                val moduleDao: ModuleDao = INSTANCE!!.moduleDao()

//                PopulateDbAsyncTask(INSTANCE!!).execute()
            }
        }

    }
}