package com.example.lab5_6

sealed class Destinations (
    val route: String,
    val title: String? = null)
    {
        object HomeScreen : Destinations(
            route = "home_screen",
            title = "Заметки"
        )
        object NoteScreen : Destinations(
            route = "note_screen",
            title = "Заметкa"
        )
    }

