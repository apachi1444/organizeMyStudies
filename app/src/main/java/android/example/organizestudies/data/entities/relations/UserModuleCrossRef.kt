package android.example.organizestudies.data.entities.relations

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "moduleId"] , tableName = "user_module_table")

data class UserModuleCrossRef(
    val userId: String,
    val moduleId: String
)