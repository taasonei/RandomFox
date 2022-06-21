package com.github.taasonei.model

sealed interface Status {
    object Success : Status
    data class Error(val message: String) : Status
}