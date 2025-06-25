package com.example.lab5_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab5_6.ui.theme.Lab56Theme
import androidx.compose.runtime.mutableStateOf
import androidx.annotation.RequiresApi
import android.os.Build
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background



import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab56Theme {
                val navController: NavHostController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    Box(modifier = Modifier.background(Color.White))
                    {
                        NavigationGraph(navController)
                    }

                }

                /*Lab56Theme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        //Greeting("Android")
                    }*/
            }
        }
    }


    private val isHomeInitialize = mutableStateOf(false)
    private val isNoteInitialize = mutableStateOf(false)

    private lateinit var home: HomeActivity
    private lateinit var note: NoteActivity

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun NavigationGraph(navController: NavHostController) {
        NavHost(navController, startDestination = Destinations.HomeScreen.route)
        {
            composable(Destinations.HomeScreen.route)
            {
                isNoteInitialize.value = false
                if (!isHomeInitialize.value) {
                    isHomeInitialize.value = true
                    home = HomeActivity(navController)
                }
                home.HomeActivityScreen() // будет подсвечиваться красным, это нормально
            }
            composable(
                Destinations.NoteScreen.route + "/{note_id}",
                arguments = listOf(navArgument("note_id") { type = NavType.StringType })
            )
            {
                isHomeInitialize.value = false
                if (!isNoteInitialize.value) {
                    isNoteInitialize.value = true
                    note = NoteActivity(navController, it.arguments?.getString("note_id")!!)
                }
                note.NoteActivityScreen() // будет подсвечиваться красным, это нормально
            }
        }
    }

    @Preview
    @Composable
    fun PreviewHomeActivityScreen(){
        val navController = rememberNavController()
        HomeActivity(navController).HomeActivityScreen()
    }
}






/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab56Theme {
        Greeting("Android")
    }
}*/