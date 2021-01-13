package domain.model

import domain.model.serialization.InstantSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class ListItem(
    val description: String,

    val quantity: Int = 1,

    @Serializable( with = InstantSerializer::class )
    val timeAdded: Instant
)
