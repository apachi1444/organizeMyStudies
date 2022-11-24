package android.example.organizestudies.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    companion object {
        private val time: Date = Calendar.getInstance().time
        private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val current: String = formatter.format(time)

        fun getDateNow(): Date {
            return time
        }

    }
}