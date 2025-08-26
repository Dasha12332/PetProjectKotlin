package com.example.currencyconverter.data.mapper

import com.example.currencyconverter.data.dataSource.remote.dto.RateDto
import com.example.currencyconverter.domain.entity.CurrencyRate


class RateMapper {
    fun toEntity(dto: RateDto): CurrencyRate {
        return CurrencyRate(dto.currency, dto.value)
    }
}