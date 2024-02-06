package com.example.shop.ui.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.data.local.OfflineRepository
import com.example.shop.data.local.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val repository: OfflineRepository
): ViewModel() {

    var userInfo by mutableStateOf(
        UserInfoUiState(
            name = "",
            surname = "",
            phoneNumber = ""
        )
    )
        private set

    var registrationUiState: RegistrationUiState by mutableStateOf(
        RegistrationUiState(
            isNameValid = true,
            isSurnameValid = true,
            isPhoneNumberValid = false,
            isCanEnter = false
        )
    )
        private set

    var startScreen: StartScreen by mutableStateOf(StartScreen.Loading)


    init {
        viewModelScope.launch {
            if (repository.getUser() != null) {
                userInfo = userToUserInfoUiState()
                startScreen = StartScreen.Entered
            }
            else {
                startScreen = StartScreen.NotEntered
            }
        }
    }

    fun updateName(input: String) {
        userInfo = userInfo.copy(name = input)
        for (letter in input) {
            if (letter !in 'А'..'Я' && letter !in 'а'..'я') {
                registrationUiState = registrationUiState.copy(isNameValid = false)
                return
            }
        }
        registrationUiState = registrationUiState.copy(isNameValid = true)
        checkCanEnter()
    }

    fun updateSurname(input: String) {
        userInfo = userInfo.copy(surname = input)
        for (letter in input) {
            if (letter !in 'А'..'Я' && letter !in 'а'..'я') {
                registrationUiState = registrationUiState.copy(isSurnameValid = false)
                return
            }
        }
        registrationUiState = registrationUiState.copy(isSurnameValid = true)
        checkCanEnter()
    }

    fun updateNumber(input: String) {
        if (input.length == 1 && input == "7") {
            return
        }
        val onlyDigits = input.filter { it.isDigit() }
        if (onlyDigits.length > 10) return
        userInfo = userInfo.copy(phoneNumber = onlyDigits)
        registrationUiState = registrationUiState.copy(
            isPhoneNumberValid = userInfo.phoneNumber.length == 10
        )
        checkCanEnter()
    }

    fun saveUser() {
        viewModelScope.launch {
            repository.saveUser(
                User(
                    id = 1,
                    name = userInfo.name,
                    surName =  userInfo.surname,
                    phoneNumber = userInfo.phoneNumber
                )
            )
        }
    }

    fun exit() {
        startScreen = StartScreen.NotEntered
        registrationUiState = RegistrationUiState(
            isNameValid = true,
            isSurnameValid = true,
            isPhoneNumberValid = false,
            isCanEnter = false
        )
        viewModelScope.launch {
            repository.deleteUser(
                User(
                    id = 1,
                    name = userInfo.name,
                    surName = userInfo.surname,
                    phoneNumber = userInfo.phoneNumber
                )
            )
        }
        userInfo = UserInfoUiState(
            name = "",
            surname = "",
            phoneNumber = ""
        )
    }

    private fun checkCanEnter() {
        registrationUiState = registrationUiState.copy(
            isCanEnter = registrationUiState.isNameValid &&
                    registrationUiState.isSurnameValid &&
                    registrationUiState.isPhoneNumberValid &&
                    userInfo.name.isNotEmpty() &&
                    userInfo.surname.isNotEmpty()
        )
    }

    private suspend fun userToUserInfoUiState(): UserInfoUiState {
        val user = repository.getUser()!!
        return UserInfoUiState(
            name = user.name,
            surname = user.surName,
            phoneNumber = user.phoneNumber
        )
    }

}

enum class StartScreen {
    Loading,
    Entered,
    NotEntered,
    FirstEnter
}

data class RegistrationUiState(
    val isNameValid: Boolean,
    val isSurnameValid: Boolean,
    val isPhoneNumberValid: Boolean,
    val isCanEnter: Boolean
)

data class UserInfoUiState(
    val name: String,
    val surname: String,
    val phoneNumber: String
)