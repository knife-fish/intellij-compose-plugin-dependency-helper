package com.github.evil0th.intellijcomposeplugindependencyhelper.chatApp.model

import com.github.evil0th.intellijcomposeplugindependencyhelper.chatApp.model.ChatMessage.ChatMessageType.AI_THINKING
import com.github.evil0th.intellijcomposeplugindependencyhelper.chatApp.model.ChatMessage.ChatMessageType.TEXT
import com.github.evil0th.intellijcomposeplugindependencyhelper.weatherApp.model.Searchable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

private val timeFormatter: DateTimeFormatter? = DateTimeFormatter.ofPattern("HH:mm")

data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val content: String,
    val author: String,
    val isMyMessage: Boolean = false,
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val type: ChatMessageType = TEXT
) : Searchable {

    enum class ChatMessageType {
        AI_THINKING,
        TEXT;
    }

    @JvmOverloads
    fun formattedTime(dateTimeFormatter: DateTimeFormatter? = timeFormatter): String {
        return timestamp.format(dateTimeFormatter)
    }


    fun isTextMessage(): Boolean = this.type == TEXT

    fun isAIThinkingMessage(): Boolean = this.type == AI_THINKING

    override fun matches(query: String): Boolean {
        if (query.isBlank()) return false

        return content.contains(query, ignoreCase = true)
    }
}
