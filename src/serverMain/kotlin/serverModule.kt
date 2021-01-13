import domain.model.ListItem
import domain.repos.ItemRepoImpl
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.html.HTML
import kotlinx.serialization.json.Json

val itemRepo by lazy { ItemRepoImpl() }

fun Application.serverModule( testing: Boolean = false ) {

    install( ContentNegotiation) {
        json(
            Json {
                isLenient = true
                encodeDefaults = true
            }
        )

        routing {
            get("/") {
                call.respondHtml(HttpStatusCode.OK, HTML::index)
            }

            route("/api" ) {

                get("/item") {
                    call.respond( itemRepo.getOpenItems().first() )
                }
            }
            static("/static") {
                resources()
            }
        }
    }


}