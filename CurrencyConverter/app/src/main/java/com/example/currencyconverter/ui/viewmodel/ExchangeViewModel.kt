package com.example.currencyconverter.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.entity.Account
import com.example.currencyconverter.domain.entity.Transaction
import com.example.currencyconverter.domain.repository.AccountRepository
import com.example.currencyconverter.domain.repository.TransactionRepository
import com.example.currencyconverter.domain.entity.CurrencyCart
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class ExchangeViewModel(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    fun onExchangeConfirmed(sellCart: CurrencyCart, buyCart: CurrencyCart) {
        viewModelScope.launch {
            val sellCurrencyCode = sellCart.code
            val buyCurrencyCode = buyCart.code
            val sellAmount = sellCart.rate ?: return@launch
            val buyAmount = buyCart.inputAmount ?: return@launch

            // Обновление аккаунта продавца
            val sellAccount = accountRepository.getAccount(sellCurrencyCode)
                ?: Account(sellCurrencyCode, 0.0)
            val updatedSellAccount = sellAccount.copy(amount = sellAccount.amount - sellAmount)
            accountRepository.updateAccount(updatedSellAccount)

            // Обновление аккаунта покупателя
            val buyAccount = accountRepository.getAccount(buyCurrencyCode)
                ?: Account(buyCurrencyCode, 0.0)
            val updatedBuyAccount = buyAccount.copy(amount = buyAccount.amount + buyAmount)
            accountRepository.updateAccount(updatedBuyAccount)

            // Сохранение транзакции
            val transaction = Transaction(
                fromCurrency = sellCurrencyCode,
                toCurrency = buyCurrencyCode,
                fromAmount = sellAmount,
                toAmount = buyAmount,
                dateTime = LocalDateTime.now()
            )
            transactionRepository.addTransaction(transaction)
        }
    }
}
