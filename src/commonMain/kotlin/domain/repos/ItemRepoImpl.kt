package domain.repos

import domain.model.ListItem
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock

class ItemRepoImpl : ItemRepo {
    override suspend fun getOpenItems(): List<ListItem> {
        delay(200)
        return listOf(
            ListItem("First thing", 1, Clock.System.now() ),
            ListItem("Thing two", 2, Clock.System.now() )
        )
    }
}