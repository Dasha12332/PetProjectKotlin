package com.example.currencyconverter.ui.navigation


sealed class Destination(val route: String) {
    object Currencies : Destination("currencies")
    object Exchange : Destination("exchange/{buyCode}/{sellCode}") {
        fun createRoute(buyCode: String, sellCode: String) = "exchange/$buyCode/$sellCode"
    }
   // object Exchange : Destination("exchange/{buyCode}/{sellCode}")


    object TransactionsList  : Destination("transactions_list")

}