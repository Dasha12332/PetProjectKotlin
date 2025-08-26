package com.example.currencyconverter.domain.entity

import java.time.LocalDateTime

data class Transaction(
    val id: Int? = null,
    val fromCurrency: String,
    val toCurrency: String,
    val fromAmount: Double,
    val toAmount: Double,
    val dateTime: LocalDateTime
)