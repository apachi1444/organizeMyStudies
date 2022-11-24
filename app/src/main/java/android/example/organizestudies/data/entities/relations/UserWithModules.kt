package android.example.organizestudies.data.entities.relations

import android.example.organizestudies.data.entities.Module
import android.example.organizestudies.data.entities.User
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class UserWithModules(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "moduleName",
        associateBy = Junction(UserModuleCrossRef::class)
    )
    val modules: List<Module>
)