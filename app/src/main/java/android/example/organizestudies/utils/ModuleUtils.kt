package android.example.organizestudies.utils

import android.example.organizestudies.R
import android.example.organizestudies.data.entities.Module
import android.example.organizestudies.data.entities.User
import android.example.organizestudies.data.entities.enums.Grades
import android.example.organizestudies.data.entities.enums.HashTagsModules

class ModuleUtils {


    companion object {

        private val moduleSpringBoot = Module(
            "Spring Boot", "Mr Atlas", Grades.GI.toString(), "5th Year",
            1, StringsUtils.convertEnumToString(HashTagsModules.BackEnd), R.drawable.springboot
        )
        private val moduleBI = Module(
            "BI", "Mr Ameur", Grades.GI.toString(), "5th Year",
            1, StringsUtils.convertEnumToString(HashTagsModules.Data), R.drawable.bi
        )
        private val moduleMobile = Module(
            "Mobile", "Mme Bouzid", Grades.GI.toString(), "5th Year",
            1, StringsUtils.convertEnumToString(HashTagsModules.Mobile), R.drawable.mobile
        )
        private val moduleUnix = Module(
            "Unix", "Mr Oumoun", Grades.GI.toString(), "5th Year",
            1, StringsUtils.convertEnumToString(HashTagsModules.Data), R.drawable.unix_image
        )
        private val moduleOracle = Module(
            "Oracle", "Mr Massaq", Grades.GI.toString(), "5th Year",
            1, StringsUtils.convertEnumToString(HashTagsModules.Data), R.drawable.oracle
        )
        private val modules = arrayListOf(
            moduleBI, moduleSpringBoot, moduleMobile, moduleUnix, moduleOracle
        )
        fun getAllModules(): ArrayList<Module> {
            return modules
        }
        fun getModules(user: User): ArrayList<Module> {
            val finalList: ArrayList<Module> = java.util.ArrayList()
            val grade = user.grade
            val levelStudy = user.levelStudy
            modules.forEach {
                if (it.grade == grade && it.levelStudy == levelStudy) {
                    finalList.add(it)
                }
            }
            return finalList
        }
    }
}