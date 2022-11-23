package android.example.organizestudies.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Module
    (
    @PrimaryKey
    var moduleId: String,
    var moduleName: String,
    var professor: String,
    var grade: String,
    var levelStudy: String,
    var period: Int,
    var hashTag: String,
    var imageModule: Int
)