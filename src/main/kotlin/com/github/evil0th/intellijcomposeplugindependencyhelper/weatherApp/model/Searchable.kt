package com.github.evil0th.intellijcomposeplugindependencyhelper.weatherApp.model

/**
 * Represents an entity that can be filtered by a search query.
 */
interface Searchable {
    fun matches(query: String): Boolean
}