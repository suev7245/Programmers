fun main() {
    val solutionClass = HelloWorld()
    val answer = solutionClass.solution("hello algorithm")
    println (answer)
}

class HelloWorld {
    fun solution(string: String): String {
        return string
    }
}
