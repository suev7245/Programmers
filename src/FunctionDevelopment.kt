import kotlin.math.ceil

fun main() {
    val solutionClass = FunctionDevelopment()
    val progresses = intArrayOf(93, 30, 55)
    val speeds = intArrayOf(1, 30, 5)
    val answer = solutionClass.solution(progresses, speeds)
    answer.map {
        println(it)
    }
}

class FunctionDevelopment {
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        val answerList = mutableListOf<Int>()

        val restTime = progresses.mapIndexed { index, progress ->
            ceil((100 - progress) / speeds[index].toDouble()).toInt()
        }

        var temp: Int = 0
        var count = 0
        restTime.forEachIndexed { index, time ->
            if (time <= temp) {
                count++
            } else {
                if (count != 0) answerList.add(count)
                temp = time
                count = 1
            }
        }
        if (count != 0) answerList.add(count)

        return answerList.toIntArray()
    }
}