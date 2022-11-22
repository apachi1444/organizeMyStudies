package android.example.organizestudies.utils

import java.util.*

class StringsUtils {
    companion object {
        fun generateRandomUUID(): String {
            return UUID.randomUUID().toString()
        }

        fun <T> convertEnumToString(value: T): String {
            return value.toString()
        }
    }
}