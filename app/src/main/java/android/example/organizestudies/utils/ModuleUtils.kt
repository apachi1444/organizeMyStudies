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
        private val moduleSysDist = Module(
            "Systèmes distribués", "Mr Atlas", Grades.GI.toString(), "5th Year",
            1, StringsUtils.convertEnumToString(HashTagsModules.Data), R.drawable.sysdist
        )
        private val moduleSysTempsReel = Module(
            "Systèmes temps réel", "Mme Zrikem", Grades.GI.toString(), "5th Year",
            1, StringsUtils.convertEnumToString(HashTagsModules.Data), R.drawable.systempsreel
        )
        private val moduleManagement2 = Module(
            "Management 2", "Mme Harouni", Grades.GI.toString(), "5th Year",
            1, StringsUtils.convertEnumToString(HashTagsModules.Data), R.drawable.management
        )
        private val moduleMethode__ingenieure_de_travail = Module(
            "Methode d'ingenieure de travail",
            "M Dakkak",
            Grades.GIL.toString(),
            "5th Year",
            1,
            StringsUtils.convertEnumToString(HashTagsModules.Industriel),
            R.drawable.methode_ingenieur_de_travail
        )
        private val moduleDeveloppementDurrable = Module(
            "NNOVATION, ENVIRONNEMENT & DEVELOPPEMENT DURABLE",
            "M BENMOUSSA",
            Grades.GIL.toString(),
            "5th Year",
            1,
            StringsUtils.convertEnumToString(HashTagsModules.Industriel),
            R.drawable.innovation
        )
        private val moduleReingenierieDesProcessusPersonnel = Module(
            "REINGENIERIE DES PROCESSUS OPERATIONNELS",
            "M BENMOUSSA",
            Grades.GIL.toString(),
            "5th Year",
            1,
            StringsUtils.convertEnumToString(HashTagsModules.Industriel),
            R.drawable.reingenieurie
        )
        private val module_CONCEPTION_AVANCEE = Module(
            "CONCEPTION AVANCEE",
            "M EL BAHIR",
            Grades.GIL.toString(),
            "5th Year",
            1,
            StringsUtils.convertEnumToString(HashTagsModules.Electrique),
            R.drawable.CONCEPTION_AVANCEE
        )
        private val module_AUTOMATIQUE_AVANCEE = Module(
            "AUTOMATIQUE AVANCEE",
            "M HAMZAOUI",
            Grades.GIL.toString(),
            "5th Year",
            1,
            StringsUtils.convertEnumToString(HashTagsModules.Electrique),
            R.drawable.AUTOMATIQUE_AVANCEE
        )
        private val module_VIRTUALISATION_CLOUD_COMPUTING = Module(
            "VIRTUALISATION, CLOUD COMPUTING", "M EL BAAMRANI",
            Grades.GRT.toString(), "5th Year", 1,
            StringsUtils.convertEnumToString(HashTagsModules.Reseau), R.drawable.envi_juridique
        )

        private val module_environnement_juridique = Module(
            "ENVIRONNEMENT JURIDIQUE",
            "M EZZAHI",
            Grades.GIL.toString(),
            "4th Year",
            2,
            StringsUtils.convertEnumToString(HashTagsModules.Industriel),
            R.drawable.envi_juridique
        )

        private val modules = arrayListOf(
            moduleBI,
            moduleSpringBoot,
            moduleMobile,
            moduleUnix,
            moduleOracle,
            moduleSysDist,
            moduleSysTempsReel,
            moduleManagement2,
            moduleMethode__ingenieure_de_travail,
            moduleDeveloppementDurrable,
            module_environnement_juridique
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