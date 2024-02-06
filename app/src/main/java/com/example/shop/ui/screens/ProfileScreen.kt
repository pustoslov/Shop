package com.example.shop.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shop.R
import com.example.shop.ui.theme.Grey
import com.example.shop.ui.theme.Orange
import com.example.shop.ui.theme.Pink
import com.example.shop.ui.ui_components.ExitButton
import com.example.shop.ui.ui_components.FavoritesButton
import com.example.shop.ui.ui_components.HollowProfileButton
import com.example.shop.ui.ui_components.ProfileButton
import com.example.shop.ui.view_models.RegistrationViewModel


@Composable
fun ProfileScreen(
    registrationViewModel: RegistrationViewModel,
    favoriteItemsCount: Int,
    onFavoritesClick: () -> Unit,
    onExitClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp, horizontal = 10.dp)
            .verticalScroll(rememberScrollState())
        ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProfileButton(
            name = registrationViewModel.userInfo.name,
            surname = registrationViewModel.userInfo.surname,
            phoneNumber = registrationViewModel.userInfo.phoneNumber,
            modifier = Modifier
                .padding(bottom = 20.dp)
        )

        FavoritesButton(
            count = favoriteItemsCount,
            modifier = Modifier
                .padding(vertical = 4.dp),
            onClick = { onFavoritesClick() }
        )

        HollowProfileButton(
            iconResource = R.drawable.shops,
            text = "Магазины",
            tint = Pink,
            modifier = Modifier
                .padding(vertical = 4.dp)
        )
        HollowProfileButton(
            iconResource = R.drawable.contact,
            text = "Обратная связь",
            tint = Orange,
            modifier = Modifier
                .padding(vertical = 4.dp)
        )
        HollowProfileButton(
            iconResource = R.drawable.offer,
            text = "Оферта",
            tint = Grey,
            modifier = Modifier
                .padding(vertical = 4.dp)
        )
        HollowProfileButton(
            iconResource = R.drawable.refund,
            text = "Возврат товара",
            tint = Grey,
            modifier = Modifier
                .padding(vertical = 4.dp)
        )

        Spacer(
            modifier = Modifier
                .weight(1f)
        )

        ExitButton(
            onClick = {
                onExitClick()
                registrationViewModel.exit()
            }
        )
    }
}