package com.example.currencyconverter.ui.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.data.dataSource.room.ConverterDatabase
import com.example.currencyconverter.data.mapper.AccountMapper
import com.example.currencyconverter.data.repository.AccountRepositoryImpl
import com.example.currencyconverter.ui.viewmodel.CurrenciesViewModel

class CurrenciesViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrenciesViewModel::class.java)) {
            val db = ConverterDatabase.getInstance(context)
            val accountRepository = AccountRepositoryImpl(
                db.accountDao(),
                AccountMapper()
            )
            return CurrenciesViewModel(accountRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}