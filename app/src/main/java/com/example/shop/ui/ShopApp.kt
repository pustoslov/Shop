package com.example.shop.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.shop.ui.navigation.BottomBar
import com.example.shop.ui.navigation.NavGraph
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shop.ui.navigation.TopBar
import com.example.shop.ui.screens.LoadingScreen
import com.example.shop.ui.view_models.CatalogViewModel
import com.example.shop.ui.view_models.RegistrationViewModel
import com.example.shop.ui.view_models.StartScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopApp(
    registrationViewModel: RegistrationViewModel = viewModel(),
    catalogViewModel: CatalogViewModel = viewModel()
) {

    val navController = rememberNavController()

    if (registrationViewModel.startScreen == StartScreen.Loading) {
        LoadingScreen()
    } else {

        val status = registrationViewModel.startScreen

        Scaffold(
            topBar = {
                TopBar(navController = navController)
            },
            bottomBar = {
                if (status != StartScreen.NotEntered) {
                    BottomBar(navController = navController)
                }
            }
        ) {
            Box(
                modifier = Modifier.padding(it)
            ) {
                NavGraph(
                    navController = navController,
                    status = status,
                    registrationViewModel = registrationViewModel,
                    catalogViewModel = catalogViewModel
                )
            }
        }
    }

}

