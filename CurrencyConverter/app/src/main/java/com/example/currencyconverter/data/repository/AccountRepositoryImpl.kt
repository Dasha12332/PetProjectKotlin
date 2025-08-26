package com.example.currencyconverter.data.repository

import com.example.currencyconverter.data.dataSource.room.account.dao.AccountDao
import com.example.currencyconverter.data.mapper.AccountMapper
import com.example.currencyconverter.domain.entity.Account
import com.example.currencyconverter.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccountRepositoryImpl(
    private val accountDao: AccountDao,
    private val mapper: AccountMapper
) : AccountRepository {

    override suspend fun getAccounts(): List<Account> {
        return accountDao.getAll().map { mapper.toEntity(it) }
    }

    override fun getAccountsFlow(): Flow<List<Account>> {
        return accountDao.getAllAsFlow().map { list -> list.map { mapper.toEntity(it) } }
    }

    override suspend fun updateAccount(account: Account) {
        accountDao.insertAll(mapper.toDbo(account.currencyCode, account.amount))
    }

    override suspend fun getAccount(currencyCode: String): Account? {
        return accountDao.getAll().find { it.code == currencyCode }?.let { mapper.toEntity(it) }
    }
}