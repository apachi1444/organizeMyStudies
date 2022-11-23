package android.example.organizestudies.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class File(
    @PrimaryKey
    var fileId: String,
    var filename: String,
    var extension: String,
    var moduleIdCorresponding: String,
    var starred: Boolean,
    var lastTimeOpen: Date
)