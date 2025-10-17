package com.github.evil0th.intellijcomposeplugindependencyhelper.chatApp.viewmodel

import com.github.evil0th.intellijcomposeplugindependencyhelper.chatApp.model.ChatMessage

data class ChatListUiState(
    val messages: List<ChatMessage> = emptyList(),
) {
    companion object Companion {
        val EMPTY = ChatListUiState()
    }
}