package android.example.organizestudies.utils

import androidx.room.TypeConverter
import java.util.*

object TypeConverters {

    @TypeConverter
    @JvmStatic
    fun dateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    @JvmStatic
    fun longToDate(long: Long): Date? {
        return if (long == null) {
            null
        } else {
            Date(long)
        }
    }


}