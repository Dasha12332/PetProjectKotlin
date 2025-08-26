package com.example.currencyconverter.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.domain.repository.AccountRepository
import com.example.currencyconverter.domain.repository.TransactionRepository
import com.example.currencyconverter.ui.viewmodel.ExchangeViewModel

class ExchangeViewModelFactory(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExchangeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExchangeViewModel(accountRepository, transactionRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
