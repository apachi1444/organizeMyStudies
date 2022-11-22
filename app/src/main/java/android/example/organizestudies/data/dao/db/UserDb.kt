package android.example.organizestudies.data.dao.db

import android.content.Context
import android.example.organizestudies.data.dao.FileDao
import android.example.organizestudies.data.dao.ModuleDao
import android.example.organizestudies.data.dao.UserDao
import android.example.organizestudies.data.dao.UserModuleCrossRefDao
import android.example.organizestudies.data.entities.File
import android.example.organizestudies.data.entities.Module
import android.example.organizestudies.data.entities.User
import android.example.organizestudies.data.entities.enums.HashTagsModules
import android.example.organizestudies.data.entities.relations.UserModuleCrossRef
import android.example.organizestudies.utils.StringsUtils
import android.os.AsyncTask
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(
    entities = [User::class, File::class, Module::class, UserModuleCrossRef::class],
    version = 12,
    exportSchema = true
)
abstract class UserDb : RoomDatabase() {

    abstract fun userDbDao(): UserDao
    abstract fun moduleDao(): ModuleDao
    abstract fun fileDao(): FileDao
    abstract fun userModuleCrossRefDao() : UserModuleCrossRefDao


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
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }

        private val roomCallback: Callback = object : RoomDatabase.Callback() {
            override fun onCreate(@NonNull db: SupportSQLiteDatabase) {
                super.onCreate(db)
                val moduleDao: ModuleDao = INSTANCE!!.moduleDao()

//                PopulateDbAsyncTask(INSTANCE!!).execute()
            }
        }

        private class PopulateDbAsyncTask(db: UserDb) :
            AsyncTask<Void?, Void?, Void?>() {
            private var moduleDao: ModuleDao = db.moduleDao()

            @Deprecated("Deprecated in Java")
            override fun doInBackground(vararg p0: Void?): Void? {
                val module = Module(
                    StringsUtils.generateRandomUUID(),
                    "Spring Boot",
                    false,
                    "Mr Atlas",
                    "GI",
                    "5th Year",
                    1,
                    StringsUtils.convertEnumToString(HashTagsModules.BackEnd)
                )
                val module1 = Module(
                    StringsUtils.generateRandomUUID(),
                    "BI",
                    false,
                    "Mr Ameur",
                    "GI",
                    "5th Year",
                    1,
                    StringsUtils.convertEnumToString(HashTagsModules.Data)
                )
                moduleDao.insert(module)
                moduleDao.insert(module1)
                return null
            }
        }

    }
}