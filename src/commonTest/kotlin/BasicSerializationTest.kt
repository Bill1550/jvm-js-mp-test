import domain.model.ListItem
import kotlinx.datetime.Instant
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotSame

class BasicSerializationTest {

    private val jsonPretty = Json {
        prettyPrint = true
    }

    private val jsonApi = Json {
        prettyPrint = false
        encodeDefaults = false
        ignoreUnknownKeys = true
    }

    /**
     * Round trip test of serialization of a Like object.
     */
    @Test
    fun serializeItem() {

        val likeObj = ListItem(
            description = "Ketchup",
            quantity = 2,
            timeAdded = Instant.parse("2015-01-01T01:00:00Z")
        )

        val itemJson = """{"description":"Ketchup","quantity":2,"timeAdded":"2015-01-01T01:00:00Z"}"""

        println( jsonPretty.encodeToString(likeObj))

        val itemSerialized = jsonApi.encodeToString(likeObj)

        assertEquals( itemJson, itemSerialized )

        val likeDecoded = jsonApi.decodeFromString<ListItem>(itemSerialized)

        println("Like decoded: $likeDecoded")
        assertEquals( likeObj, likeDecoded)
        assertNotSame(likeObj, likeDecoded)
    }
}