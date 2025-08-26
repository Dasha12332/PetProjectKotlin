package com.example.diplomv1.View.Elements

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun rememberPickerState() = remember { PickerState() }

class PickerState {
    private val _selectedItem = mutableStateOf("")
    var selectedItem: String by _selectedItem
}