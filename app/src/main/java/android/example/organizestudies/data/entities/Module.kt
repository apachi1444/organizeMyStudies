package android.example.organizestudies.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Module
    (
    @PrimaryKey
    var moduleId: String,
    var moduleName: String,
)