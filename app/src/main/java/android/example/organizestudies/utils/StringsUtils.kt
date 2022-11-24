package android.example.organizestudies.utils

import java.util.*

class StringsUtils {
    companion object {

        const val MESSAGE_SUCCESS_FILE_UPLOADED: String = "File Added !"
        const val MESSAGE_ERROR_WHEN_FILE_UPLOADED: String = "Error ! Please Try Again !"

        fun generateRandomUUID(): String {
            return UUID.randomUUID().toString()
        }

        fun <T> convertEnumToString(value: T): String {
            return value.toString()
        }
    }
}