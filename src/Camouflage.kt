fun main() {
    val answer = Camouflage().moreBetterSolution(
        arrayOf(arrayOf("a","A"), arrayOf("b","A"), arrayOf("c","A"), arrayOf("a","B"))
    )
    println (answer)
}

class Camouflage {
    fun solution(clothes: Array<Array<String>>): Int {
        var answer = 1
        val clothesMap = HashMap<String, Int>()
        clothes.forEach {
            clothesMap[it[1]] = clothesMap[it[1]]?.plus(1) ?: 1
        }
        clothesMap.forEach {
            answer *= it.value + 1
        }
        return answer-1
    }

    fun betterSolution(clothes: Array<Array<String>>): Int {
        return clothes
            .groupBy { it[1] }
            .values
            .map { it.size + 1 }
            .reduce(Int::times) // .reduce{ a,b -> a*b } // .reduce{ a,b -> a.times(b) }
            .minus(1)
    }

    fun moreBetterSolution(clothes: Array<Array<String>>): Int {
        return clothes.groupBy { it[1] }.values
            .map { it.size + 1 }
            .fold(1, Int::times) // .fold(1){ a,b -> a*b }
            .minus(1)
    }
}
