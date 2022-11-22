package android.example.organizestudies.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Module
    (
    @PrimaryKey
    var moduleId: String,
    var moduleName: String,
    var starred: Boolean,
    val professor: String,
    val grade: String,
    val levelStudy: String,
    val period: Int,
    val hashTag: String
)