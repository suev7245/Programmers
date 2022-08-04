import kotlin.math.abs

fun main() {
    val solutionClass = One()
    val answer = solutionClass.solution(arrayOf("M--NN-", "-M----", "-CC--M", "-N----", "N-M-C-", "-M----"))
    println(answer)
}

class One {
    fun solution(seat: Array<String>): Int {
        val isChecked = Array<IntArray>(seat.size) { IntArray(seat.size) }

        seat.forEachIndexed { row, s ->
            s.forEachIndexed { col, c ->
                if (c == 'C') findPoisoned(row, col, isChecked, seat)
            }
        }

        var result = 0
        isChecked.forEach {
            it.forEach {
//                print(it)
                if (it == 1) result++
            }
//            println()
        }
//        println()
        return result
    }

    fun findPoisoned(row: Int, col: Int, isChecked: Array<IntArray>, seat: Array<String>) {
//        println()
//        println("let's find $row, $col")
//        println("let's find N $row, $col")
        for (i in -3..3) {
            if (row + i < 0 || row + i >= seat.size) continue
            for (j in -3..3) {
                if (col + j < 0 || col + j >= seat.size) continue

                if (abs(i) + abs(j) > 3) continue
                println("$i, $j = ${row+1}, ${col+j}")

                if (seat[row + i][col + j] == 'N') isChecked[row + i][col + j] = 1
            }
        }

//        println("let's find M $row, $col")
        for (i in -2..2) {
            if (row + i < 0 || row + i >= seat.size) continue
            for (j in -2..2) {
                if (col + j < 0 || col + j >= seat.size) continue

                if (abs(i) + abs(j) > 2) continue
//                println("$i, $j")

                if (seat[row + i][col + j] == 'M') isChecked[row + i][col + j] = 1
            }
        }
    }
}