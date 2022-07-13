package com.github.taasonei.randomfox.model

sealed interface Status {
    object Success : Status
    data class Error(val message: String) : Status
}