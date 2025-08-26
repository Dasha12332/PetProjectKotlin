package com.example.diplomv1.Model.DataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PhysicDao {

    @Query("SELECT np.namePhysicParameter, f.formula\n" +
            "FROM name_physic_parameter np\n" +
            "JOIN formula f ON np.idNamePhysicParameter = f.idNamePhysicParameter\n" +
            "JOIN section s ON f.idSection = s.idSection\n" +
            "WHERE s.nameSection = :nameSection;")
    fun getPairFormulaAndNamePhysicParameter(nameSection: String):List <FormulaPair>

    @Query("SELECT npp.namePhysicParameter AS namePhysicParameter, um.unitMeasure AS unitMeasure\n" +
            "        FROM unit_measure um\n" +
            "        INNER JOIN name_physic_parameter npp ON um.idNamePhysicParameter = npp.idNamePhysicParameter\n" +
            "        INNER JOIN section s ON um.idSection = s.idSection\n" +
            "        WHERE s.nameSection = :nameSection")
    fun getPairUnitMeasureAndNamePhysicParameter(nameSection: String):List<UnitMeasurePair>

    @Query("SELECT npp.namePhysicParameter, um.unitMeasure FROM unit_measure um INNER JOIN name_physic_parameter npp ON um.idNamePhysicParameter = npp.idNamePhysicParameter INNER JOIN section s ON um.idSection = s.idSection")
    suspend fun getAllPairs(): List<UnitMeasurePair>

    @Query("SELECT * FROM section")
    suspend fun getAllSections(): List<Section>
    @Query("SELECT * FROM name_physic_parameter")
    suspend fun getAllParameters(): List<NamePhysicParameter>
    @Query("SELECT * FROM unit_measure")
    suspend fun getAllUM(): List<UnitMeasure>

    @Query("SELECT np.namePhysicParameter, lp.letter\n" +
            "FROM name_physic_parameter np\n" +
            "JOIN letter_physic_parameter lp ON np.idNamePhysicParameter = lp.idNamePhysicParameter\n" +
            "JOIN section s ON lp.idSection = s.idSection\n" +
            "WHERE s.nameSection = :nameSection;")
    fun getPairLetterAndNamePhysicParameter(nameSection: String):List<LetterPair>

    //работает везде с ним закончено
    @Query("SELECT nameSection FROM section")
    fun getSection(): List<String>

    @Query("SELECT th.nameTheory, th.textTheory, th.idPhysicModel\n" +
            "    FROM theory th\n" +
            "    JOIN section s ON th.idSection = s.idSection\n" +
            "    WHERE s.nameSection = :nameSection;")
    fun getTheory(nameSection: String):List<SectionTheory>

    @Query("SELECT t.textTask\n" +
            "    FROM task t\n" +
            "    JOIN section s ON t.idSection = s.idSection\n" +
            "    WHERE s.nameSection = :nameSection;")
    fun getTask(nameSection: String): LiveData<List<String>>

    @Insert
    suspend fun insertSection(section: Section)

    @Insert
    suspend fun insertTheory(theory: Theory)

    @Insert
    suspend fun insertPhysicModel(physicModel: PhysicModel)

    @Insert
    suspend fun insertAchievement(achievement: Achievement)

    @Insert
    suspend fun insertTask(task: Task)

    @Insert
    suspend fun insertVariantAnswer(variantAnswer: VariantAnswer)

    @Insert
    suspend fun insertFormula(formula: Formula)

    @Insert
    suspend fun insertNamePhysicParameter(namePhysicParameter: NamePhysicParameter)

    @Insert
    suspend fun insertLetterPhysicParameter(letterPhysicParameter: LetterPhysicParameter)

    @Insert
    suspend fun insertUnitMeasure(unitMeasure: UnitMeasure)


    //дописать получение вариантов ответа текущего этапа для текущей задачи
    //дописать что-то про достижения
    // Новые методы
    @Query("SELECT va.textVariantAnswer FROM variant_answer va JOIN task t ON va.idTask = t.idTask WHERE t.idTask = :taskId AND va.step = :currentStep")
    fun getTaskAnswers(taskId: Long, currentStep: Int): LiveData<List<String>>

    //временная мера потом востановить
    /*@Query("SELECT a.nameAchievement, a.status FROM achievement a JOIN section s ON a.idSection = s.idSection WHERE s.nameSection = :nameSection")
    fun getAchievements(nameSection: String): LiveData<Map<String, Boolean>>*/

    @Update
    suspend fun updateAchievement(achievement: Achievement)

    @Query("SELECT * FROM achievement WHERE idAchievement = :id")
    suspend fun getAchievementById(id: Long): Achievement
}