package com.flexcode.yummy.core.utils

sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
}
