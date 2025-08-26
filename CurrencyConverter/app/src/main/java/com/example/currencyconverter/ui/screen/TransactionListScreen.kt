package com.example.currencyconverter.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.currencyconverter.R
import com.example.currencyconverter.di.DependencyContainer
import com.example.currencyconverter.domain.entity.Transaction
import com.example.currencyconverter.ui.viewmodel.TransactionViewModel
import com.example.currencyconverter.ui.viewmodel.factory.TransactionViewModelFactory
import java.time.format.DateTimeFormatter

@Composable
fun TransactionsListScreen() {
    val viewModel: TransactionViewModel = viewModel(
        factory = TransactionViewModelFactory(DependencyContainer.transactionRepository)
    )
    val transactions by viewModel.transactions.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.transaction_history),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (transactions.isEmpty()) {
            Text(
                stringResource(R.string.transaction_status),
                modifier = Modifier.padding(16.dp)
            )
        } else {
            TransactionsList(transactions = transactions)
        }
    }
}

@Composable
fun TransactionsList(transactions: List<Transaction>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(transactions) { transaction ->
            TransactionCard(transaction = transaction)
        }
    }
}

@Composable
fun TransactionCard(transaction: Transaction) {
    val dateString = transaction.dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Дата
            Text(
                text = dateString,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            TransactionDetailRow(
                label = stringResource(R.string.sell),
                amount = transaction.fromAmount,
                currency = transaction.fromCurrency,
                isBold = true
            )

            TransactionDetailRow(
                label = stringResource(R.string.buy),
                amount = transaction.toAmount,
                currency = transaction.toCurrency,
                isBold = true
            )
        }
    }
}

@Composable
fun TransactionDetailRow(
    label: String,
    amount: Double,
    currency: String,
    isBold: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            style = if (isBold) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "${"%.2f".format(amount)} $currency",
            style = if (isBold)
                MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            else
                MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
