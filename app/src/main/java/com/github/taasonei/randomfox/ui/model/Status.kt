package com.github.taasonei.randomfox.ui.model

sealed interface Status {
    object Success : Status
    data class Error(val message: String) : Status
}