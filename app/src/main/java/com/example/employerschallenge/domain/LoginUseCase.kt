package com.example.employerschallenge.domain

import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCase @Inject constructor() {

    companion object {
        private const val DEFAULT_USER_ID = "1"
        private const val DEFAULT_DELAY = 1500L
    }

    fun login(userId: String): Flow<Boolean> = flow {
        delay(DEFAULT_DELAY)
        emit(userId == DEFAULT_USER_ID)
    }
}
