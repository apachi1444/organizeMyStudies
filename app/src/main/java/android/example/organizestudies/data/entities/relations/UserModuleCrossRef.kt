package android.example.organizestudies.data.entities.relations

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_module_table")
data class UserModuleCrossRef(
    @PrimaryKey
    var userModuleIdsCombined: String,
    var username: String,
    var moduleName: String,
    var starred: Boolean,
    var moduleImage : Int
)

