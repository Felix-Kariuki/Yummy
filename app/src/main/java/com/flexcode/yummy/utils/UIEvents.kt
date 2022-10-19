package com.flexcode.yummy.utils

sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
}
