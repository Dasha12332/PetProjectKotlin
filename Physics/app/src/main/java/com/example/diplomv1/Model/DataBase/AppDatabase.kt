package com.example.diplomv1.Model.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/*@Database(
    version = 1,
    entities = [ListItem::class]
)
abstract class AppDatabase: RoomDatabase(){
    abstract fun listItemDao(): ListItemDao
}*/

@Database(entities = [(Section::class),
    (Theory::class),
    (PhysicModel::class),
    (Achievement::class),
    (Task::class),
    (VariantAnswer::class),
    (Formula::class),
    (NamePhysicParameter::class),
    (LetterPhysicParameter::class),
    (UnitMeasure::class)], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun physicDao(): PhysicDao

    // реализуем синглтон
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "physic_db"
                    ).fallbackToDestructiveMigration()
                        .addCallback(DatabaseCallback(context.applicationContext))
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    private class DatabaseCallback(private val context: Context) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            CoroutineScope(Dispatchers.IO).launch {
                val database = getInstance(context)
                val dao = database.physicDao()
                // Вставьте начальные данные
                dao.insertSection(Section(idSection = 1,nameSection = "Механика"))
                dao.insertSection(Section(idSection = 2, nameSection = "Молекулярная физика и термодинамика"))
                dao.insertSection(Section(idSection = 3,nameSection = "Оптика"))
                dao.insertSection(Section(idSection = 4,nameSection = "Электродинамика"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "масса"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "время"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "сила тока"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "температура"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "количество вещества"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "сила света"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "площадь"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "Объем"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "скорость"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "ускорение"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "импульс"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "сила"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "работа"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "энергия"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "мощность"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "давление"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "плотность"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "количество теплоты"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "заряд"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "напряжение"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "сопротивление"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "магнитный поток"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "частота"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "угол падения"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "угол отражения"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "угол преломления"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "угловая скорость"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "угловое ускорение"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "момент инерции"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "момент импульса"))
                dao.insertNamePhysicParameter(NamePhysicParameter(namePhysicParameter = "момент силы"))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 1, letter = "m", idNamePhysicParameter = 1))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 1, letter = "t", idNamePhysicParameter = 2))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 4, letter = "I", idNamePhysicParameter = 3))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 2, letter = "T", idNamePhysicParameter = 4))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 2, letter = "n", idNamePhysicParameter = 5))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 3, letter = "J", idNamePhysicParameter = 6))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 1, letter = "S", idNamePhysicParameter = 7))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 1, letter = "V", idNamePhysicParameter = 8))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 1, letter = "\u24E5", idNamePhysicParameter = 9))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 1, letter = "a", idNamePhysicParameter = 10))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 1, letter = "p", idNamePhysicParameter = 11))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 1, letter = "F", idNamePhysicParameter = 12))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 1, letter = "A", idNamePhysicParameter = 13))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 1, letter = "E", idNamePhysicParameter = 14))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 1, letter = "N", idNamePhysicParameter = 15))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 1, letter = "p", idNamePhysicParameter = 16))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 1, letter = "ρ", idNamePhysicParameter = 17))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 2, letter = "Q", idNamePhysicParameter = 18))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 4, letter = "q", idNamePhysicParameter = 19))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 4, letter = "U", idNamePhysicParameter = 20))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 4, letter = "R", idNamePhysicParameter = 21))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 4, letter = "Ф", idNamePhysicParameter = 22))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 1, letter = "ν", idNamePhysicParameter = 23))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 3, letter = "α", idNamePhysicParameter = 24))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 3, letter = "β", idNamePhysicParameter = 25))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 3, letter = "γ", idNamePhysicParameter = 26))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 1, letter = "ω", idNamePhysicParameter = 27))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 1, letter = "ε", idNamePhysicParameter = 28))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 1, letter = "I", idNamePhysicParameter = 29))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 1, letter = "L", idNamePhysicParameter = 30))
                dao.insertLetterPhysicParameter(LetterPhysicParameter(idSection = 1, letter = "M", idNamePhysicParameter = 31))
                dao.insertUnitMeasure(UnitMeasure(idSection = 1, unitMeasure = "килограмм (кг)", idNamePhysicParameter = 1))
                dao.insertUnitMeasure(UnitMeasure(idSection = 1, unitMeasure = "секунда (с)", idNamePhysicParameter = 2))
                dao.insertUnitMeasure(UnitMeasure(idSection = 4, unitMeasure = "ампер (А)", idNamePhysicParameter = 3))
                dao.insertUnitMeasure(UnitMeasure(idSection = 2, unitMeasure = "кельвин (К)", idNamePhysicParameter = 4))
                dao.insertUnitMeasure(UnitMeasure(idSection = 2, unitMeasure = "моль", idNamePhysicParameter = 5))
                dao.insertUnitMeasure(UnitMeasure(idSection = 3, unitMeasure = "кандела (кд)", idNamePhysicParameter = 6))
                dao.insertUnitMeasure(UnitMeasure(idSection = 1, unitMeasure = "м²", idNamePhysicParameter = 7))
                dao.insertUnitMeasure(UnitMeasure(idSection = 1, unitMeasure = "м³", idNamePhysicParameter = 8))
                dao.insertUnitMeasure(UnitMeasure(idSection = 1, unitMeasure = "м/с", idNamePhysicParameter = 9))
                dao.insertUnitMeasure(UnitMeasure(idSection = 1, unitMeasure = "м/с²", idNamePhysicParameter = 10))
                dao.insertUnitMeasure(UnitMeasure(idSection = 1, unitMeasure = "кг·м/с", idNamePhysicParameter = 11))
                dao.insertUnitMeasure(UnitMeasure(idSection = 1, unitMeasure = "ньютон (Н)", idNamePhysicParameter = 12))
                dao.insertUnitMeasure(UnitMeasure(idSection = 1, unitMeasure = "джоуль (Дж)", idNamePhysicParameter = 13))
                dao.insertUnitMeasure(UnitMeasure(idSection = 1, unitMeasure = "джоуль (Дж)", idNamePhysicParameter = 14))
                dao.insertUnitMeasure(UnitMeasure(idSection = 1, unitMeasure = "ватт (Вт)", idNamePhysicParameter = 15))
                dao.insertUnitMeasure(UnitMeasure(idSection = 1, unitMeasure = "паскаль (Па)", idNamePhysicParameter = 16))
                dao.insertUnitMeasure(UnitMeasure(idSection = 1, unitMeasure = "кг/м³", idNamePhysicParameter = 17))
                dao.insertUnitMeasure(UnitMeasure(idSection = 2, unitMeasure = "джоуль (Дж)", idNamePhysicParameter = 18))
                dao.insertUnitMeasure(UnitMeasure(idSection = 4, unitMeasure = "кулон (Кл)", idNamePhysicParameter = 19))
                dao.insertUnitMeasure(UnitMeasure(idSection = 4, unitMeasure = "вольт (В)", idNamePhysicParameter = 20))
                dao.insertUnitMeasure(UnitMeasure(idSection = 4, unitMeasure = "Ом", idNamePhysicParameter = 21))
                dao.insertUnitMeasure(UnitMeasure(idSection = 4, unitMeasure = "вебер (Вб)", idNamePhysicParameter = 22))
                dao.insertUnitMeasure(UnitMeasure(idSection = 1, unitMeasure = "герц (Гц)", idNamePhysicParameter = 23))
                dao.insertUnitMeasure(UnitMeasure(idSection = 3, unitMeasure = "радиан (рад)", idNamePhysicParameter = 24))
                dao.insertUnitMeasure(UnitMeasure(idSection = 3, unitMeasure = "радиан (рад)", idNamePhysicParameter = 25))
                dao.insertUnitMeasure(UnitMeasure(idSection = 3, unitMeasure = "радиан (рад)", idNamePhysicParameter = 26))
                dao.insertUnitMeasure(UnitMeasure(idSection = 1, unitMeasure = "рад/с", idNamePhysicParameter = 27))
                dao.insertUnitMeasure(UnitMeasure(idSection = 1, unitMeasure = "рад/с²", idNamePhysicParameter = 28))
                dao.insertUnitMeasure(UnitMeasure(idSection = 1, unitMeasure = "кг·м²", idNamePhysicParameter = 29))
                dao.insertUnitMeasure(UnitMeasure(idSection = 1, unitMeasure = "кг·м²/c", idNamePhysicParameter = 30))
                dao.insertUnitMeasure(UnitMeasure(idSection = 1, unitMeasure = "кг·м²/с²", idNamePhysicParameter = 31))


                dao.insertPhysicModel(PhysicModel(namePhysicModel = "CarActivity"))
                dao.insertTheory(Theory(nameTheory = "Равномерное движение", idPhysicModel = 1, idSection = 1, textTheory = "Равномерное движение...\n Надо будет придумать сюда какой-то объясняющий теоритический текст"))
                dao.insertTheory(Theory(nameTheory = "Равноускоренное движение", idPhysicModel = 1, idSection = 1, textTheory = "Равноускоренное движение...\n Надо будет придумать сюда какой-то объясняющий теоритический текст"))
            }
        }
    }
}
