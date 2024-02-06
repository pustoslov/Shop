package com.example.shop.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shop.ui.theme.Grey
import com.example.shop.ui.theme.sanFrancisco
import com.example.shop.ui.ui_components.CustomTextField
import com.example.shop.ui.ui_components.PhoneNumberField
import com.example.shop.ui.ui_components.TextButton
import com.example.shop.ui.view_models.RegistrationViewModel
import com.example.shop.ui.view_models.StartScreen

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
        ) {
            CustomTextField(
                isValid = viewModel.registrationUiState.isNameValid,
                text = viewModel.userInfo.name,
                onValueChange = { text -> viewModel.updateName(text) },
                placeholder = "Имя",
                onClear = { viewModel.updateName("") },
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )
            CustomTextField(
                isValid = viewModel.registrationUiState.isSurnameValid,
                text = viewModel.userInfo.surname,
                onValueChange = { text -> viewModel.updateSurname(text) },
                placeholder = "Фамилия",
                onClear = { viewModel.updateSurname("") },
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )
            PhoneNumberField(
                number = viewModel.userInfo.phoneNumber,
                placeholder = "Номер телефона",
                onClear = { viewModel.updateNumber("") },
                onValueChange = { number -> viewModel.updateNumber(number) },
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )
            TextButton(
                text = "Войти",
                isActive = viewModel.registrationUiState.isCanEnter,
                onClick = {
                    if (viewModel.registrationUiState.isCanEnter) {
                        viewModel.saveUser()
                        viewModel.startScreen = StartScreen.FirstEnter
                    }
                },
                modifier = Modifier
                    .padding(18.dp)
            )
        }
        Box(
            modifier = Modifier
                .wrapContentSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    text = "Нажимая кнопку \"Войти\", Вы принимаете",
                    fontFamily = sanFrancisco,
                    textAlign = TextAlign.Center,
                    fontSize = 11.sp,
                    color = Grey,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = "условия программы лояльности",
                    fontFamily = sanFrancisco,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 11.sp,
                    color = Grey,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}