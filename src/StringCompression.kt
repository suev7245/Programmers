import java.lang.Integer.min

fun main() {
    val solutionClass = StringCompression()
    val answer = solutionClass.solution("abababab")
    println(answer)
}

class StringCompression {
    fun solution(s: String): Int {
        var answer = s.length

        (1..s.length / 2).map { size ->
            answer = min(count(size, s), answer)
        }
        return answer
    }

    private fun count(size: Int, s: String): Int {
        var answer = s.length

        var before: String = ""
        var count = 0

        s.chunked(size).map {
            if (it == before) {
                count++
                answer -= size
            } else {
                if (count > 1) answer += count.toString().length
                before = it
                count = 1
            }
        }
        if (count > 1) answer += count.toString().length

        return answer
    }
}