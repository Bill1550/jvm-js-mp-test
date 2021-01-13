import domain.model.ListItem
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.div
import react.dom.input

external interface WelcomeProps : RProps {
    var name: String
}

data class WelcomeState(val name: String) : RState

@JsExport
class Welcome(props: WelcomeProps) : RComponent<WelcomeProps, WelcomeState>(props) {

    private val httpClient: HttpClient = HttpClient() {
        install( JsonFeature ) {
            serializer = KotlinxSerializer()
        }
    }


    init {
        state = WelcomeState(props.name)


        MainScope().launch {
            delay(2000)
            console.log("delayed output")
            setState(
                WelcomeState( name= "Item=${getItem()}" )
            )

        }


    }

    private suspend fun getItem(): ListItem {
        return httpClient.get( "http://localhost:8080/api/item"){
            header( HttpHeaders.ContentEncoding, "application/json")
        }
    }

//    override fun componentWillMount() { // causes an error in the running JS, console.error(e) - undefined
//        super.componentWillMount()
//    }

    override fun RBuilder.render() {
        div {
            +"Hello, ${state.name}"
        }
        input {
            attrs {
                type = InputType.text
                value = state.name
                onChangeFunction = { event ->
                    setState(
                        WelcomeState(name = (event.target as HTMLInputElement).value)
                    )
                }
            }
        }
        div {
            + "Http client: ${httpClient?.engine?.let {it::class?.simpleName}}"
        }


    }


}
