package com.example.shop.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shop.ui.screens.CartScreen
import com.example.shop.ui.screens.CatalogScreen
import com.example.shop.ui.screens.FavoritesScreen
import com.example.shop.ui.screens.ItemScreen
import com.example.shop.ui.screens.MainScreen
import com.example.shop.ui.screens.ProfileScreen
import com.example.shop.ui.screens.PromoScreen
import com.example.shop.ui.screens.RegistrationScreen
import com.example.shop.ui.view_models.CatalogViewModel
import com.example.shop.ui.view_models.RegistrationViewModel
import com.example.shop.ui.view_models.StartScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    status: StartScreen,
    registrationViewModel: RegistrationViewModel,
    catalogViewModel: CatalogViewModel
) {

    NavHost(
        navController = navController,
        startDestination = when (status) {
            StartScreen.Entered -> NavigationScreen.Catalog.route
            StartScreen.NotEntered -> NavigationScreen.Registration.route
            else -> NavigationScreen.Main.route
        }
        ) {

        composable(NavigationScreen.Main.route) {
            MainScreen()
        }
        composable(NavigationScreen.Catalog.route) {
            CatalogScreen(
                catalogViewModel = catalogViewModel,
                onItemClick = { navController.navigate(NavigationScreen.Item.route) }
            )
        }
        composable(NavigationScreen.Cart.route) {
            CartScreen()
        }
        composable(NavigationScreen.Promo.route) {
            PromoScreen()
        }
        composable(NavigationScreen.Profile.route) {
            ProfileScreen(
                registrationViewModel = registrationViewModel,
                favoriteItemsCount = catalogViewModel.favoritesIds.size,
                onFavoritesClick = { navController.navigate(NavigationScreen.Favorites.route) },
                onExitClick = { catalogViewModel.deleteAllFavorites() }
            )
        }
        composable(NavigationScreen.Registration.route) {
            RegistrationScreen(
                viewModel = registrationViewModel
            )
        }
        composable(NavigationScreen.Favorites.route) {
            FavoritesScreen(
                catalogViewModel = catalogViewModel,
                onItemClick = { navController.navigate(NavigationScreen.Item.route) }
            )
        }
        composable(NavigationScreen.Item.route) {
            ItemScreen(
                item = catalogViewModel.currentItem,
                catalogViewModel = catalogViewModel
            )
        }
    }

}