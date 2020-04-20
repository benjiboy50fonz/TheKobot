import kotlinx.coroutines.*

fun main() = runBlocking{
    repeat(100_100) {
        launch {
            delay(100L)
            print(".")
        }
    }
}

// Damn, that didn't kill my memory. I love coroutines lol.