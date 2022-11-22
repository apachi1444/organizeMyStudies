package android.example.organizestudies.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast


class Utils {

    companion object {

        const val
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

        fun readingFromSharedPreferences(context: Context, key: String): String? {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("MySharedPref", MODE_PRIVATE)
            return sharedPreferences.getString(key, "")
        }

        fun insertingKeyIntoSharedPreferences(
            context: Context,
            key: String,
            value: String
        ) {

            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("MySharedPref", MODE_PRIVATE)
            val myEdit = sharedPreferences.edit()
            myEdit.putString(key, value)
            myEdit.apply()
        }

        fun deletingInformationFromSharedPreferencesWhenLogOut(context: Context) {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("MySharedPref", MODE_PRIVATE)
            val myEdit = sharedPreferences.edit()

            sharedPreferences.all.forEach { _ ->
                myEdit.clear()
            }

            myEdit.apply()
        }

        fun checkUserLoggedIn(context: Context): Boolean {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("MySharedPref", MODE_PRIVATE)

            if (sharedPreferences.getString("username", "").toString() == "") return false
            return true
        }

        fun <T> startActivity(context: Context, classActivity: Class<T>) {
            val intent = Intent(context, classActivity)
            context.startActivity(intent)
        }

        fun<T> navigateToSpecificFragment(context: Context , activity: T){

        }

    }

}