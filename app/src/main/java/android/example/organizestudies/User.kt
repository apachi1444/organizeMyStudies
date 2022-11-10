package android.example.organizestudies

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate=true
    )
    var userId: Long = 0, var username: String)