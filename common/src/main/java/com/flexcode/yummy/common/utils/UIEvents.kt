package com.flexcode.yummy.common.utils

sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
}
