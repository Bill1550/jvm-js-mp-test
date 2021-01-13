package domain.repos

import domain.model.ListItem

interface ItemRepo {

    suspend fun getOpenItems(): List<ListItem>
}