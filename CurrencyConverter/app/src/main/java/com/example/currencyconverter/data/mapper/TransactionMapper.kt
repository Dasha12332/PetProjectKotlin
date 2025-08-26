package com.example.currencyconverter.data.mapper

import com.example.currencyconverter.data.dataSource.room.transaction.dbo.TransactionDbo
import com.example.currencyconverter.domain.entity.Transaction


class TransactionMapper {
    fun toDbo(transaction: Transaction): TransactionDbo {
        return TransactionDbo(
            id = 0, // auto-generated
            from = transaction.fromCurrency,
            to = transaction.toCurrency,
            fromAmount = transaction.fromAmount,
            toAmount = transaction.toAmount,
            dateTime = transaction.dateTime
        )
    }

    fun toEntity(dbo: TransactionDbo): Transaction {
        return Transaction(
            id = dbo.id,
            fromCurrency = dbo.from,
            toCurrency = dbo.to,
            fromAmount = dbo.fromAmount,
            toAmount = dbo.toAmount,
            dateTime = dbo.dateTime
        )
    }
}