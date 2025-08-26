package com.example.currencyconverter.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.currencyconverter.R
import coil.compose.AsyncImage
import com.example.currencyconverter.domain.entity.Currency
import com.example.currencyconverter.domain.entity.CurrencyCart
import com.example.currencyconverter.ui.mapper.StringMapper
import com.example.currencyconverter.ui.viewmodel.CurrenciesViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.currencyconverter.ui.viewmodel.SharedViewModel
import com.example.currencyconverter.ui.viewmodel.factory.CurrenciesViewModelFactory


@Composable
fun CurrenciesScreen(
    shared: SharedViewModel,
    //onExchangeClick: (CurrencyCart, CurrencyCart) -> Unit,
    //onConvertClick: () -> Unit
    navController: NavController
) {
    val context = LocalContext.current
    val viewModel: CurrenciesViewModel = viewModel(
        factory = CurrenciesViewModelFactory(context)
    )
    //val shared: SharedViewModel = viewModel()
    //val viewModel: CurrenciesViewModel = viewModel(factory = viewModelFactory)
    val currencies = viewModel.currencies
    val baseCurrency = currencies.firstOrNull()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.currency),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(currencies) { index, currency  ->
                CurrencyCardUI(
                    currency = viewModel.currencies[index],
                    modifier = Modifier.clickable {

                        val baseAmount = viewModel.currencies[0].inputAmount ?: 1.0
                        if (baseAmount == 1.0) {
                            viewModel.selectCurrency(index) // меняем местами
                        } else {
                            shared.currencies = viewModel.currencies
                            navController.navigate("exchange/${shared.currencies[0].code}/${shared.currencies[index].code}")
                        }
                    },
                    index = index,
                    onAmountChange = { newAmount ->
                        viewModel.updateBaseAmount(newAmount)
                    },
                    onClearClick = {
                        viewModel.updateBaseAmount(1.0)
                        viewModel.resetCurrencies()
                    }
                )
            }
        }

        Button(
            onClick = {
                navController.navigate("transactions_list")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(stringResource(R.string.transaction_list), fontSize = 18.sp)
        }
    }
}


@Composable
fun CurrencyCardUI(currency: CurrencyCart,
                  modifier: Modifier,
                   index: Int,
                   onAmountChange: (Double) -> Unit,
                   onClearClick: () -> Unit) {
    var inputText by remember { mutableStateOf(currency.inputAmount?.toString() ?: "1") }

    val isEditable = index == 0
    val showClearButton = isEditable && (inputText.toDoubleOrNull() ?: 1.0) != 1.0

    Card(
        modifier = modifier
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
                // Символ валюты (нельзя редактировать)
                Text(
                    text = stringResource(currency.symbol),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                if (isEditable) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        @OptIn(ExperimentalMaterial3Api::class)
                        TextField(
                            value = inputText,
                            onValueChange = {newText ->
                                if (
                                    newText.length <= 8 &&
                                    newText.all { it.isDigit() || it == '.' } &&
                                    newText.count { it == '.' } <= 1 &&
                                    newText.substringAfter('.', "").length <= 2
                                ) {
                                    inputText = newText
                                    onAmountChange(newText.toDoubleOrNull() ?: 1.0)
                                }
                            },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            textStyle = LocalTextStyle.current.copy(
                                textAlign = TextAlign.Start,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent, // Убираем фон
                                unfocusedIndicatorColor = Color.LightGray, // Линия снизу
                                focusedIndicatorColor = Color.Black, // Линия снизу при фокусе
                                disabledIndicatorColor = Color.Transparent,
                                //textColor = Color.Black,
                                cursorColor = Color.Black
                            ),
                            shape = RectangleShape, // Убираем скругления
                        )
                        if (showClearButton) {
                            IconButton(
                                onClick = {
                                    inputText = "1"
                                    onClearClick()
                                },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Clear",
                                    tint = Color.Red
                                )
                            }

                        }
                    }
                } else {
                    // Просто текст, не редактируемый
                    Text(
                        text = currency.rate.toString(),
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

            }
        }
    }
}
