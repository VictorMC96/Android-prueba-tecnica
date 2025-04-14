package com.example.employerschallenge.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employerschallenge.core.coroutines.CoroutinesDispatchers
import com.example.employerschallenge.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val _loadingUiState = MutableStateFlow(false)
    val loadingUiState = _loadingUiState.asStateFlow()

    private val _validUseEvent = Channel<Boolean>()
    val validUseEvent = _validUseEvent.receiveAsFlow()

    fun login(userId: String) {
        viewModelScope.launch(coroutinesDispatchers.io) {
            _loadingUiState.value = true
            loginUseCase.login(userId.trim()).collect {
                _loadingUiState.value = false
                _validUseEvent.send(it)
            }
        }
    }
}
