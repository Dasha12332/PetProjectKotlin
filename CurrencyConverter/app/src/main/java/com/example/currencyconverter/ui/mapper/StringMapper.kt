package com.example.currencyconverter.ui.mapper

import com.example.currencyconverter.R

class StringMapper {
    fun getResCodeId(currencyCode: String): Int {
        return when (currencyCode) {
            "USD" -> R.string.code_usd
            "GBP" -> R.string.code_gbp
            "EUR" -> R.string.code_eur
            "AUD" -> R.string.code_aud
            "BGN" -> R.string.code_bgn
            "BRL" -> R.string.code_brl
            "CAD" -> R.string.code_cad
            "CHF" -> R.string.code_chf
            "CNY" -> R.string.code_cny
            "CZK" -> R.string.code_czk
            "DKK" -> R.string.code_dkk
            "HKD" -> R.string.code_hkd
            "HRK" -> R.string.code_hrk
            "HUF" -> R.string.code_huf
            "IDR" -> R.string.code_idr
            "ILS" -> R.string.code_ils
            "INR" -> R.string.code_inr
            "ISK" -> R.string.code_isk
            "JPY" -> R.string.code_jpy
            "KRW" -> R.string.code_krw
            "MXN" -> R.string.code_mxn
            "MYR" -> R.string.code_myr
            "NOK" -> R.string.code_nok
            "NZD" -> R.string.code_nzd
            "PHP" -> R.string.code_php
            "PLN" -> R.string.code_pln
            "RON" -> R.string.code_ron
            "RUB" -> R.string.code_rub
            "SEK" -> R.string.code_sek
            "SGD" -> R.string.code_sgd
            "THB" -> R.string.code_thb
            "TRY" -> R.string.code_try
            "ZAR" -> R.string.code_zar
            else -> -1
        }
    }

    public fun getResSymbolId(currencyCode: String): Int {
        return when (currencyCode) {
            "EUR" -> R.string.symbol_eur
            "AUD" -> R.string.symbol_aud
            "BGN" -> R.string.symbol_bgn
            "BRL" -> R.string.symbol_brl
            "CAD" -> R.string.symbol_cad
            "CHF" -> R.string.symbol_chf
            "CNY" -> R.string.symbol_cny
            "CZK" -> R.string.symbol_czk
            "DKK" -> R.string.symbol_dkk
            "GBP" -> R.string.symbol_gbp
            "HKD" -> R.string.symbol_hkd
            "HRK" -> R.string.symbol_hrk
            "HUF" -> R.string.symbol_huf
            "IDR" -> R.string.symbol_idr
            "ILS" -> R.string.symbol_ils
            "INR" -> R.string.symbol_inr
            "ISK" -> R.string.symbol_isk
            "JPY" -> R.string.symbol_jpy
            "KRW" -> R.string.symbol_krw
            "MXN" -> R.string.symbol_mxn
            "MYR" -> R.string.symbol_myr
            "NOK" -> R.string.symbol_nok
            "NZD" -> R.string.symbol_nzd
            "PHP" -> R.string.symbol_php
            "PLN" -> R.string.symbol_pln
            "RON" -> R.string.symbol_ron
            "RUB" -> R.string.symbol_rub
            "SEK" -> R.string.symbol_sek
            "SGD" -> R.string.symbol_sgd
            "THB" -> R.string.symbol_thb
            "TRY" -> R.string.symbol_try
            "USD" -> R.string.symbol_usd
            "ZAR" -> R.string.symbol_zar
            else -> -1
        }
    }

    fun getResCurrencyId(currencyCode: String): Int {
        return when (currencyCode) {
            "EUR" -> R.string.currency_eur
            "AUD" -> R.string.currency_aud
            "BGN" -> R.string.currency_bgn
            "BRL" -> R.string.currency_brl
            "CAD" -> R.string.currency_cad
            "CHF" -> R.string.currency_chf
            "CNY" -> R.string.currency_cny
            "CZK" -> R.string.currency_czk
            "DKK" -> R.string.currency_dkk
            "GBP" -> R.string.currency_gbp
            "HKD" -> R.string.currency_hkd
            "HRK" -> R.string.currency_hrk
            "HUF" -> R.string.currency_huf
            "IDR" -> R.string.currency_idr
            "ILS" -> R.string.currency_ils
            "INR" -> R.string.currency_inr
            "ISK" -> R.string.currency_isk
            "JPY" -> R.string.currency_jpy
            "KRW" -> R.string.currency_krw
            "MXN" -> R.string.currency_mxn
            "MYR" -> R.string.currency_myr
            "NOK" -> R.string.currency_nok
            "NZD" -> R.string.currency_nzd
            "PHP" -> R.string.currency_php
            "PLN" -> R.string.currency_pln
            "RON" -> R.string.currency_ron
            "RUB" -> R.string.currency_rub
            "SEK" -> R.string.currency_sek
            "SGD" -> R.string.currency_sgd
            "THB" -> R.string.currency_thb
            "TRY" -> R.string.currency_try
            "USD" -> R.string.currency_usd
            "ZAR" -> R.string.currency_zar
            else -> -1
        }
    }

    fun getResHttpId(currencyCode: String): Int {
        return when (currencyCode) {
            "EUR" -> R.string.flag_url_eu
            "AUD" -> R.string.flag_url_au
            "BGN" -> R.string.flag_url_bg
            "BRL" -> R.string.flag_url_br
            "CAD" -> R.string.flag_url_ca
            "CHF" -> R.string.flag_url_ch
            "CNY" -> R.string.flag_url_cn
            "CZK" -> R.string.flag_url_cz
            "DKK" -> R.string.flag_url_dk
            "GBP" -> R.string.flag_url_gb
            "HKD" -> R.string.flag_url_hk
            "HRK" -> R.string.flag_url_hr
            "HUF" -> R.string.flag_url_hu
            "IDR" -> R.string.flag_url_id
            "ILS" -> R.string.flag_url_il
            "INR" -> R.string.flag_url_in
            "ISK" -> R.string.flag_url_is
            "JPY" -> R.string.flag_url_jp
            "KRW" -> R.string.flag_url_kr
            "MXN" -> R.string.flag_url_mx
            "MYR" -> R.string.flag_url_my
            "NOK" -> R.string.flag_url_no
            "NZD" -> R.string.flag_url_nz
            "PHP" -> R.string.flag_url_ph
            "PLN" -> R.string.flag_url_pl
            "RON" -> R.string.flag_url_ro
            "RUB" -> R.string.flag_url_ru
            "SEK" -> R.string.flag_url_se
            "SGD" -> R.string.flag_url_sg
            "THB" -> R.string.flag_url_th
            "TRY" -> R.string.flag_url_tr
            "USD" -> R.string.flag_url_us
            "ZAR" -> R.string.flag_url_za
            else -> -1
        }
    }
}
