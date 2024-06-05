package com.example.metalcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.metalcalculator.ui.theme.MetalCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MetalCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val scope = rememberCoroutineScope()

                    NavHost(navController, startDestination = "list") {
                        composable("list") {
                            ListScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                            )
                        }
                        composable(
                            "detail?name={name}&image={image}",
                            arguments = listOf(
                                navArgument("name") { type = NavType.StringType },
                                navArgument("image") { type = NavType.IntType },
                            )
                        ) {
                            DetailScreen(
                                modifier = Modifier.padding(innerPadding),
                                name = it.arguments?.getString("name"),
                                image = it.arguments?.getInt("image"),
                            )
                        }
                    }
                }
            }
        }
    }
}
