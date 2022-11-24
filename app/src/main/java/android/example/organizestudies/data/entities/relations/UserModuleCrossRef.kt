package android.example.organizestudies.data.entities.relations

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_module_table")
data class UserModuleCrossRef(

    @PrimaryKey
    val userModuleIdsCombined: String,

    @Embedded
    val userModuleNames: UserModuleId,

    var starred: Boolean,
)

