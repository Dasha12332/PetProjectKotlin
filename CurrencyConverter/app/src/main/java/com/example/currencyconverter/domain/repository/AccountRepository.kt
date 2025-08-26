package com.example.currencyconverter.domain.repository

import com.example.currencyconverter.domain.entity.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    suspend fun getAccounts(): List<Account>
    fun getAccountsFlow(): Flow<List<Account>>
    suspend fun getAccount(currencyCode: String): Account?
    suspend fun updateAccount(account: Account)
}
