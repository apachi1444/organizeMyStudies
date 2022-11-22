package android.example.organizestudies.data.entities.relations

import android.example.organizestudies.data.entities.File
import android.example.organizestudies.data.entities.Module
import androidx.room.Embedded
import androidx.room.Relation

class ModuleWithFiles(
    @Embedded
    val module: Module,

    @Relation(
        parentColumn = "moduleId",
        entityColumn = "moduleIdCorresponding"
    )
    val files: List<File>
)