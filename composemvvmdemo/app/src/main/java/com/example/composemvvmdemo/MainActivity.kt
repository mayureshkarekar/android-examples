package com.example.composemvvmdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composemvvmdemo.screen.BenefitScreen
import com.example.composemvvmdemo.screen.FruitScreen
import com.example.composemvvmdemo.ui.theme.ComposeMVVMDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMVVMDemoTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = ("Fruitsy")) },
                            backgroundColor = Color.Black,
                            contentColor = Color.White
                        )
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        App()
                    }
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "fruit") {
        composable(route = "fruit") {
            FruitScreen() { fruit ->
                navController.navigate("benefit/${fruit}")
            }
        }
        composable(
            route = "benefit/{fruit}",
            arguments = listOf(navArgument(name = "fruit") { type = NavType.StringType })
        ) {
            BenefitScreen()
        }
    }
}