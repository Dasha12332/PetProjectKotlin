package com.example.currencyconverter.domain.entity

data class CurrencyCart(
    val flag: Int, //код ресурса флага
    val code: String, // сокращенное название валюты
    val name: Int, // код ресурса полного имени валюты
    val symbol: Int, // код ресурса символьного обозначения валюты
    val balance: Double? = null, // баланс
    val rate: Double? = null,
    val additionalRate: Double? = null,
    val inputAmount: Double? = null
)