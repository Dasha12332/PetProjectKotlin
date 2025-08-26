package com.example.diplomv1.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.diplomv1.Model.DataBase.AppDatabase
import com.example.diplomv1.Model.DataBase.PhysicRepository
import com.example.diplomv1.Model.DataBase.Section
import com.example.diplomv1.Model.DataBase.SectionTheory
import com.example.diplomv1.Model.DataBase.UnitMeasurePair
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class DataBaseViewModel(application: Application) : ViewModel() {
    val sectionList: List<String>
    private val repository: PhysicRepository



    init {
        val physicDb = AppDatabase.getInstance(application)
        val physicDao = physicDb.physicDao()
        repository = PhysicRepository(physicDao)
        sectionList = runBlocking(Dispatchers.IO) {
            repository.getSections()
        }
    }

    suspend fun getUnitPair(section: String): Map<String, String> {
        return withContext(Dispatchers.IO) {
            repository.getUnitsWithParameters(section)
        }
    }

    suspend fun getLetterPair(section: String): Map<String, String> {
        return withContext(Dispatchers.IO) {
            repository.getLettersWithParameters(section)
        }
    }

    suspend fun getFormulaPair(section: String): Map<String, String> {
        return withContext(Dispatchers.IO) {
            repository.getFormulasWithParameters(section)
        }
    }

    suspend fun getTheory(section: String):List<SectionTheory>{
        return withContext(Dispatchers.IO) {
            repository.getTheoryContent(section)
        }
    }


}
class DataBaseViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataBaseViewModel::class.java)) {
            return DataBaseViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}