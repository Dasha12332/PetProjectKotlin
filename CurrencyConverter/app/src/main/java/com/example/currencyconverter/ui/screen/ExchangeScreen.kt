package com.example.currencyconverter.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.currencyconverter.R
import com.example.currencyconverter.data.repository.AccountRepositoryImpl
import com.example.currencyconverter.di.DependencyContainer
import com.example.currencyconverter.domain.entity.CurrencyCart
import com.example.currencyconverter.ui.navigation.NavigatorImpl
import com.example.currencyconverter.ui.viewmodel.ExchangeViewModel
import com.example.currencyconverter.ui.viewmodel.factory.ExchangeViewModelFactory

@Composable
fun ExchangeScreen(navController: NavHostController, currencyCartBuy:CurrencyCart, currencyCartSell: CurrencyCart) {
    val textNewMoneySell="- ${stringResource(currencyCartSell.symbol)} ${currencyCartSell.rate.toString()}"
    val textNewMoneyBuy="+ ${stringResource(currencyCartBuy.symbol)} ${currencyCartBuy.inputAmount.toString()}"
    val viewModel: ExchangeViewModel = viewModel(
        factory = ExchangeViewModelFactory(DependencyContainer.accountRepository, DependencyContainer.transactionRepository)
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF5F5F5))){
        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "${stringResource(currencyCartSell.name)} ${stringResource(R.string.transaction_to)}" +
                    " ${stringResource(currencyCartBuy.name)}",
            fontSize = 30.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start =  16.dp, end=16.dp)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = "${stringResource(currencyCartBuy.symbol)}1 = " +
                    "${stringResource(currencyCartSell.symbol)}${currencyCartSell.rate!!/currencyCartBuy.inputAmount!!}",
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f),
            modifier = Modifier.padding( start = 16.dp)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        CurrencyCardUI(currencyCartBuy,textNewMoneyBuy)
        Spacer(modifier = Modifier.padding(8.dp))
        CurrencyCardUI(currencyCartSell,textNewMoneySell)
        Spacer(modifier = Modifier.padding(30.dp))
        Button(
            onClick ={
                viewModel.onExchangeConfirmed(currencyCartSell, currencyCartBuy)
                NavigatorImpl(navController).navigateToCurrencies() // опционально — возвращаемся назад
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("${stringResource(R.string.transaction_buy)} ${stringResource(currencyCartBuy.name)} " +
                    "${stringResource(R.string.transaction_for)} ${stringResource(currencyCartSell.name)}"
                , fontSize = 18.sp,
                textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun CurrencyCardUI(currency: CurrencyCart, textNewMoney:String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AsyncImage(
                    model = stringResource(currency.flag),
                    contentDescription = "Country Flag",
                    modifier = Modifier.size(60.dp)
                )

                Column {
                    Text(
                        text = currency.code,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stringResource(currency.name),
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )
                    if(currency.balance != 0.0)
                    Text(
                        text = "${stringResource(R.string.balance_format)} ${stringResource(currency.symbol)} ${currency.balance}",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = textNewMoney,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}