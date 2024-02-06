package com.example.shop.ui.navigation

import com.example.shop.R

sealed class NavigationScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Main: NavigationScreen(
        route = "main",
        title = "Главная",
        icon = R.drawable.home_icon
    )
    object Catalog: NavigationScreen(
        route = "catalog",
        title = "Каталог",
        icon = R.drawable.catalog_icon
    )
    object Cart: NavigationScreen(
        route = "cart",
        title = "Корзина",
        icon = R.drawable.cart_icon
    )
    object Promo: NavigationScreen(
        route = "promo",
        title = "Акции",
        icon = R.drawable.promo_icon
    )
    object Profile: NavigationScreen(
        route = "profile",
        title = "Профиль",
        icon = R.drawable.profile_icon
    )
    object Registration: NavigationScreen(
        route = "registration",
        title = "Вход",
        icon = 0
    )
    object Favorites: NavigationScreen(
        route = "favorites",
        title = "Избранное",
        icon = 0
    )
    object Item: NavigationScreen(
        route = "item",
        title = "",
        icon = 0
    )
}
