package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ListItem(
    val description: String,
    val quantity: Int = 1
)
