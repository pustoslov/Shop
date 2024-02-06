package com.example.shop.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.shop.ui.theme.DarkGrey
import com.example.shop.ui.theme.LighterGrey
import com.example.shop.ui.theme.Pink
import com.example.shop.ui.theme.sanFrancisco

@Composable
fun BottomBar(navController: NavHostController) {

    val screens = listOf(
        NavigationScreen.Main,
        NavigationScreen.Catalog,
        NavigationScreen.Cart,
        NavigationScreen.Promo,
        NavigationScreen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Box(
        modifier = Modifier
            .height(67.dp)
            .fillMaxWidth()
            .background(LighterGrey)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 2.dp)
                .background(Color.White)
        ) {
            screens.forEach {screen->
                AddItem(
                    screen = screen,
                    navDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }

}

@Composable
fun AddItem(
    screen: NavigationScreen,
    navDestination: NavDestination?,
    navController: NavHostController
){

    BottomBarItem(
        icon = painterResource(id = screen.icon),
        text = screen.title,
        isSelected = navDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            navController.navigate(screen.route){
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        },
        modifier = Modifier
            .padding(top = 8.dp)
    )
}

@Composable
fun BottomBarItem(
    icon: Painter,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val color = if (isSelected) Pink else DarkGrey
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(24.dp)
        ) {
            Icon(
                painter = icon,
                contentDescription = "",
                tint = color
            )
        }
        Text(
            text = text,
            fontSize = 10.sp,
            fontFamily = sanFrancisco,
            color = color,
            modifier = Modifier
                .padding(top = 3.dp)
        )
    }

}