package android.example.organizestudies.data.entities.relations

import android.example.organizestudies.data.entities.File
import androidx.room.Embedded
import androidx.room.Relation

data class UserModuleCrossRefWithFiles(
    @Embedded
    val userModuleCrossRef: UserModuleCrossRef,

    @Relation(
        parentColumn = "userModuleIdsCombined",
        entityColumn = "moduleIdCorresponding"
    )
    val files: List<File>
)