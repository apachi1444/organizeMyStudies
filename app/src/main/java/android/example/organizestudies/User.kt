package android.example.organizestudies

@Entity
data class User(
    @PrimaryKey(autoGenerate=true)
    var userId: Long = 0, var username: String)