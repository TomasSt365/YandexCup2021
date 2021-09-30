package qualificationRound

var isHandled: Boolean = false

fun handleOnce(event: String?) {
    if (!isHandled) {
        isHandled = true
        println(event)
    }
}
fun main() {
    for (i in 0..9) {
        val event = "event $i"
        val t = Thread { handleOnce(event) }
        t.start()
    }
}
