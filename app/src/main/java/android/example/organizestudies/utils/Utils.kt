package android.example.organizestudies.utils

import android.content.Context
import android.content.Intent
import android.example.organizestudies.main.MainActivity
import android.util.Log
import android.widget.Toast

class Utils {

    companion object {

        val
                CHOOSE_PDF_FROM_DEVICE = 1001

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
                Log.i("boolean", it)
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

        fun getSharedPreferences() {

        }

        fun goToActivity(context: Context, classActivity: Class<MainActivity>) {
            val intent = Intent(context, classActivity)
            context.startActivity(intent)
        }

    }

}