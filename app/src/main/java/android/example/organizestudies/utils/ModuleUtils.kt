package android.example.organizestudies.utils

import android.example.organizestudies.R
import android.example.organizestudies.data.entities.Module
import android.example.organizestudies.data.entities.User
import android.example.organizestudies.data.entities.enums.HashTagsModules

class ModuleUtils {


    companion object {

        private val moduleSpringBoot = Module(
            StringsUtils.generateRandomUUID(),
            "Spring Boot",

            "Mr Atlas",
            "GI",
            "5th Year",
            1,
            StringsUtils.convertEnumToString(HashTagsModules.BackEnd),
            R.drawable.springboot
        )
        private val moduleBI = Module(
            StringsUtils.generateRandomUUID(),
            "BI",
            "Mr Ameur",
            "GI",
            "5th Year",
            1,
            StringsUtils.convertEnumToString(HashTagsModules.Data),
            R.drawable.uml_image
        )

        private val moduleMobile = Module(
            StringsUtils.generateRandomUUID(),
            "Mobile",
            "Mme Bouzid",
            "GI",
            "5th Year",
            1,
            StringsUtils.convertEnumToString(HashTagsModules.Data),
            R.drawable.uml_image
        )

        private val modules = arrayListOf(
            moduleBI, moduleSpringBoot, moduleMobile
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