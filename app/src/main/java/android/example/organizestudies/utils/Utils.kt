package android.example.organizestudies.utils

import android.content.Context
import android.widget.Toast

class Utils {

    companion object {

        fun showToast(context: Context, msg: String) {
            Toast.makeText(
                context,
                msg,
                Toast.LENGTH_SHORT
            ).show()
        }

        fun checkInputsEmptyOrNot(vararg inputs: String): Boolean {
            var finalBoolean = true
            inputs.forEach {
                if (it.isEmpty()) {
                    finalBoolean = false
                    return false
                }
            }
            return finalBoolean
        }

        fun checkPasswordAndConfirmPassword(
            password: String,
            confirmPassword: String
        ): Boolean {
            return (password == confirmPassword)
        }
    }

}