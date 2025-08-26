package com.example.diplomv1.Model.DataBase

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "section")
data class Section(
    @PrimaryKey(autoGenerate = true) var idSection: Long? = null,
    var nameSection: String
)

@Entity(tableName = "theory", foreignKeys = [
    ForeignKey(
        entity = PhysicModel::class,  // Класс сущности, на которую ссылается FK
        parentColumns = ["idPhysicModel"],          // Поле в родительской таблице (Primary Key)
        childColumns = ["idPhysicModel"],  // Поле в этой таблице (FK)
    ),
    ForeignKey(
        entity = Section::class,  // Класс сущности, на которую ссылается FK
        parentColumns = ["idSection"],          // Поле в родительской таблице (Primary Key)
        childColumns = ["idSection"],  // Поле в этой таблице (FK)
    )
])
data class Theory(
    @PrimaryKey(autoGenerate = true) var idTheory: Long? = null,
    var nameTheory: String,
    var textTheory: String,
    var idPhysicModel: Long?,
    var idSection: Long?
)

@Entity(tableName = "physic_model")
data class PhysicModel(
    @PrimaryKey(autoGenerate = true) var idPhysicModel: Long? = null,
    var namePhysicModel: String
)

@Entity(tableName = "achievement", foreignKeys = [
    ForeignKey(
        entity = Section::class,  // Класс сущности, на которую ссылается FK
        parentColumns = ["idSection"],          // Поле в родительской таблице (Primary Key)
        childColumns = ["idSection"],  // Поле в этой таблице (FK)
    )
])
data class Achievement(
    @PrimaryKey(autoGenerate = true) var idAchievement: Long? = null,
    var idSection: Long?,
    var nameAchievement: String,
    var status: Boolean
)

@Entity(tableName = "task", foreignKeys = [
    ForeignKey(
        entity = Section::class,  // Класс сущности, на которую ссылается FK
        parentColumns = ["idSection"],          // Поле в родительской таблице (Primary Key)
        childColumns = ["idSection"],  // Поле в этой таблице (FK)
    )
])
data class Task(
    @PrimaryKey(autoGenerate = true) var idTask: Long? = null,
    var idSection: Long?,
    var textTask: String
)

@Entity(tableName = "variant_answer", foreignKeys = [
    ForeignKey(
        entity = Task::class,  // Класс сущности, на которую ссылается FK
        parentColumns = ["idTask"],          // Поле в родительской таблице (Primary Key)
        childColumns = ["idTask"],  // Поле в этой таблице (FK)
    )
])
data class VariantAnswer(
    @PrimaryKey(autoGenerate = true) var idVariantAnswer: Long? = null,
    var idTask: Long?,
    var step: Int,
    var textVariantAnswer: String
)

@Entity(tableName = "formula", foreignKeys = [
        ForeignKey(
            entity = NamePhysicParameter::class,  // Класс сущности, на которую ссылается FK
            parentColumns = ["idNamePhysicParameter"],          // Поле в родительской таблице (Primary Key)
            childColumns = ["idNamePhysicParameter"],  // Поле в этой таблице (FK)
        )
    ])
data class Formula(
    @PrimaryKey(autoGenerate = true) var idFormula: Long? = null,
    var idSection: Long?,
    var formula: String,
    var idNamePhysicParameter: Long?,
)

@Entity(tableName = "name_physic_parameter")
data class NamePhysicParameter(
    @PrimaryKey(autoGenerate = true) var idNamePhysicParameter: Long? = null,
    var namePhysicParameter: String,
)

@Entity(tableName = "letter_physic_parameter", foreignKeys = [
    ForeignKey(
        entity = NamePhysicParameter::class,  // Класс сущности, на которую ссылается FK
        parentColumns = ["idNamePhysicParameter"],          // Поле в родительской таблице (Primary Key)
        childColumns = ["idNamePhysicParameter"],  // Поле в этой таблице (FK)
    )
])
data class LetterPhysicParameter(
    @PrimaryKey(autoGenerate = true) var idLetterPhysicParameter: Long? = null,
    var idSection: Long?,
    var letter: String,
    var idNamePhysicParameter: Long?
)

@Entity(tableName = "unit_measure",foreignKeys = [
    ForeignKey(
        entity = NamePhysicParameter::class,  // Класс сущности, на которую ссылается FK
        parentColumns = ["idNamePhysicParameter"],          // Поле в родительской таблице (Primary Key)
        childColumns = ["idNamePhysicParameter"],  // Поле в этой таблице (FK)
    )
])
data class UnitMeasure(
    @PrimaryKey(autoGenerate = true) var idUnitMeasure: Long? = null,
    var idSection: Long?,
    var unitMeasure: String,
    var idNamePhysicParameter: Long?
)

//для ответов на запросы
data class FormulaPair(
    val namePhysicParameter: String,
    val formula: String
)

data class UnitMeasurePair(
    val namePhysicParameter: String,
    val unitMeasure: String
)

data class LetterPair(
    val letter: String,
    val namePhysicParameter: String,
)

data class SectionTheory(
    val nameTheory: String,
    val textTheory: String,
    val idPhysicModel: Long?
)