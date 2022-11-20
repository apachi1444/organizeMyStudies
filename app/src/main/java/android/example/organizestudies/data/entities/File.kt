package android.example.organizestudies.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class File(
    @PrimaryKey
    var fileId: String,
    var filename: String,
    var extension: String
)