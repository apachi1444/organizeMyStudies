package android.example.organizestudies.utils

import android.example.organizestudies.data.entities.Module
import android.example.organizestudies.data.entities.User
import android.example.organizestudies.data.entities.enums.HashTagsModules

class ModuleUtils {


    companion object {

        private val moduleSpringBoot = Module(
            StringsUtils.generateRandomUUID(),
            "Spring Boot",
            false,
            "Mr Atlas",
            "GI",
            "5th Year",
            1,
            StringsUtils.convertEnumToString(HashTagsModules.BackEnd)
        )
        private val moduleBI = Module(
            StringsUtils.generateRandomUUID(),
            "BI",
            false,
            "Mr Ameur",
            "GI",
            "5th Year",
            1,
            StringsUtils.convertEnumToString(HashTagsModules.Data)
        )

        private val moduleMobile = Module(
            StringsUtils.generateRandomUUID(),
            "Mobile",
            false,
            "Mme Bouzid",
            "GI",
            "5th Year",
            1,
            StringsUtils.convertEnumToString(HashTagsModules.Data)
        )

        private val modules = arrayListOf(
            moduleBI, moduleSpringBoot, moduleMobile
        )

        fun getModules(user: User): ArrayList<Module> {
            var finalList: ArrayList<Module> = java.util.ArrayList()
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