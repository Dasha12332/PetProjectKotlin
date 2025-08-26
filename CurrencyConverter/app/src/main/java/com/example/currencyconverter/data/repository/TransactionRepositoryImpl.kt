package com.example.currencyconverter.data.repository

import com.example.currencyconverter.data.dataSource.room.transaction.dao.TransactionDao
import com.example.currencyconverter.data.mapper.TransactionMapper
import com.example.currencyconverter.domain.entity.Transaction
import com.example.currencyconverter.domain.repository.TransactionRepository

class TransactionRepositoryImpl(
    private val transactionDao: TransactionDao,
    private val mapper: TransactionMapper
) : TransactionRepository {

    override suspend fun getTransactions(): List<Transaction> {
        return transactionDao.getAll().map { mapper.toEntity(it) }
    }

    override suspend fun addTransaction(transaction: Transaction) {
        transactionDao.insertAll(mapper.toDbo(transaction))
    }
}