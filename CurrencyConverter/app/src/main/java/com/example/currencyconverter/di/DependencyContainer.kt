package com.example.currencyconverter.di

import android.content.Context
import androidx.room.Room
import com.example.currencyconverter.data.dataSource.room.ConverterDatabase
//import com.example.currencyconverter.data.dataSource.room.ConverterDatabase.Companion.INSTANCE
import com.example.currencyconverter.data.mapper.AccountMapper
import com.example.currencyconverter.data.mapper.TransactionMapper
import com.example.currencyconverter.data.repository.AccountRepositoryImpl
import com.example.currencyconverter.data.repository.TransactionRepositoryImpl
import com.example.currencyconverter.domain.repository.AccountRepository
import com.example.currencyconverter.domain.repository.TransactionRepository

object DependencyContainer {
    private var database: ConverterDatabase? = null

    lateinit var accountRepository: AccountRepositoryImpl
    lateinit var transactionRepository: TransactionRepositoryImpl

    fun init(context: Context) {
        database = ConverterDatabase.getInstance(context)

        accountRepository = AccountRepositoryImpl(database!!.accountDao(), AccountMapper())
        transactionRepository = TransactionRepositoryImpl(database!!.transactionDao(), TransactionMapper())
    }
}
