package android.example.organizestudies.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

//package android.example.organizestudies.data.entities
//
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import org.jetbrains.annotations.NotNull
//
//@Entity
//data class android.example.organizestudies.data.entities.User(
//    val usernameC: String,
//    val passwordC: String,
//    val gradeC: String,
//    val levelStudyC: String
//) {
//    @PrimaryKey(
//        autoGenerate = true
//    )
//    @NotNull
//    var userId: String = ""
//
//
//    var username: String = ""
//        get() = username
//        set(value) {
//            field = value
//        }
//    var password: String = ""
//        get() = password
//        set(value) {
//            field = value
//        }
//    var grade: String = ""
//        get() = grade
//        set(value) {
//            field = value
//        }
//    var levelStudy: String = ""
//        get() = levelStudy
//        set(value) {
//            field = value
//        }
//
//    init {
//        username = usernameC
//        password = passwordC
//        grade = gradeC
//        levelStudy = levelStudyC
//    }
//
//}

@Entity
class User(
    @PrimaryKey
    var username: String,
    var password: String,
    var grade: String,
    var levelStudy: String
)