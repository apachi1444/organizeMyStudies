package android.example.organizestudies.data.entities

import androidx.room.Entity

@Entity(primaryKeys = ["moduleName"])
data class Module
    (
    var moduleId: String,
    var moduleName: String,
    var professor: String,
    var grade: String,
    var levelStudy: String,
    var period: Int,
    var hashTag: String,
    var imageModule: Int
)