package com.example.diplomv1.Model.DataBase

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhysicRepository(private val physicDao: PhysicDao) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    // Section operations
    // Основные методы

    suspend fun getSections(): List<String> = withContext(Dispatchers.IO) {
        physicDao.getSection()
    }

    fun getFormulasWithParameters(nameSection: String): Map<String, String> {
        return physicDao.getPairFormulaAndNamePhysicParameter(nameSection)
        .associate { it.namePhysicParameter to it.formula }}


    fun getUnitsWithParameters(nameSection: String): Map<String, String> {
        return physicDao.getPairUnitMeasureAndNamePhysicParameter(nameSection)
            .associate { it.namePhysicParameter to it.unitMeasure }
    }

    fun getLettersWithParameters(nameSection: String): Map<String, String> {
        return physicDao.getPairLetterAndNamePhysicParameter(nameSection)
            .associate { it.namePhysicParameter to it.letter }
    }


    // Работа с теорией
    fun getTheoryContent(nameSection: String): List<SectionTheory> =
        physicDao.getTheory(nameSection)

    // Работа с задачами
    fun getTasks(nameSection: String): LiveData<List<String>> =
        physicDao.getTask(nameSection)

    fun getTaskAnswers(taskId: Long, currentStep: Int): LiveData<List<String>> =
        physicDao.getTaskAnswers(taskId, currentStep)

    // Работа с достижениями
    /*fun getAchievementsStatus(nameSection: String): LiveData<Map<String, Boolean>> =
        physicDao.getAchievements(nameSection)*/

    fun completeAchievement(achievementId: Long) {
        coroutineScope.launch(Dispatchers.IO) {
            val achievement = physicDao.getAchievementById(achievementId)
            physicDao.updateAchievement(achievement.copy(status = true))
        }
    }

    fun addNewAchievement(achievement: Achievement) {
        coroutineScope.launch(Dispatchers.IO) {
            physicDao.insertAchievement(achievement)
        }
    }

    // Дополнительные методы
    fun getPhysicalModelInfo(modelId: Long): LiveData<PhysicModel> {
        // Реализовать соответствующий метод в DAO при необходимости
        throw NotImplementedError("Add implementation in DAO")
    }
}