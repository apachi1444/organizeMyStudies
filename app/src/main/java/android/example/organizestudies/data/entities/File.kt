package android.example.organizestudies.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class File(
    @PrimaryKey
    var filename: String,
    var extension: String,
    var starred: Boolean,
    var lastTimeOpened: Date,
    var createdAt: Date,
    var moduleIdCorresponding: String,
    var moduleImage: Int,
    var moduleName : String,
    var username: String,
    var wholeUriPath : String
)