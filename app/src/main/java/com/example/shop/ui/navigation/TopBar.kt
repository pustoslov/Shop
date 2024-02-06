package com.example.shop.ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.shop.R
import com.example.shop.ui.theme.sanFrancisco

@Composable
fun TopBar(
    navController: NavHostController?,
    title: String = ""
) {

    val screens = listOf(
        NavigationScreen.Main,
        NavigationScreen.Catalog,
        NavigationScreen.Cart,
        NavigationScreen.Promo,
        NavigationScreen.Profile,
        NavigationScreen.Registration,
        NavigationScreen.Favorites
    )

    var currentTitle = title

    if (navController != null) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        for (item in screens) {
            if (item.route == currentDestination?.route)
                currentTitle = if (currentDestination.route == "profile") "Личный кабинет"
                else item.title
        }
    }

    val isNavigateBackActive = currentTitle == "Избранное" || currentTitle == ""
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (isNavigateBackActive) Arrangement.Start
            else Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (isNavigateBackActive) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 30.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            navController?.popBackStack()
                        }
                )
            }
            Text(
                text = currentTitle,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = sanFrancisco,
                modifier = Modifier
                    .then( if (currentTitle == "") Modifier.weight(1f) else Modifier)
            )
            if (currentTitle == "") {
                Icon(
                    painter = painterResource(id = R.drawable.send),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 20.dp)
                )
            }
        }
    }
}