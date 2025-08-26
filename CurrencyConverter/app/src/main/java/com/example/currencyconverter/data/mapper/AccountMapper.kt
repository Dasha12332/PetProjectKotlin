package com.example.currencyconverter.data.mapper

import com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo
import com.example.currencyconverter.domain.entity.Account

class AccountMapper {
    fun toDbo(currencyCode: String, amount: Double): AccountDbo {
        return AccountDbo(currencyCode, amount)
    }

    fun toEntity(dbo: AccountDbo): Account {
        return Account(dbo.code, dbo.amount)
    }
}