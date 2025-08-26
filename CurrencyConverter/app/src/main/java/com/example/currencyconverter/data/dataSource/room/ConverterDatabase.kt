package com.example.currencyconverter.data.dataSource.room

import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.currencyconverter.data.dataSource.room.account.dao.AccountDao
import com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo
import com.example.currencyconverter.data.dataSource.room.converter.Converters
import com.example.currencyconverter.data.dataSource.room.transaction.dao.TransactionDao
import com.example.currencyconverter.data.dataSource.room.transaction.dbo.TransactionDbo

@Database(entities = [AccountDbo::class, TransactionDbo::class], version = 1)
@TypeConverters(Converters::class)
abstract class ConverterDatabase: RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): TransactionDao


    companion object {
        @Volatile
        private var INSTANCE: ConverterDatabase? = null

        fun getInstance(context: android.content.Context): ConverterDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ConverterDatabase::class.java,
                    "converter"
                )
                    .createFromAsset("databases/converter.db")
                    .build().also { INSTANCE = it }
            }
        }
    }
}

