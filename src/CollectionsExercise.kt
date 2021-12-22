import java.util.*
import java.util.stream.Stream
import kotlin.collections.ArrayList

fun main() {
    CollectionsExercise().takeExercise()
}

class CollectionsExercise {
    fun takeExercise() {
        val takeEx = listOf<Int>(1, 2, 3)
        takeEx.take(2).forEach { println(it) } // 12
        takeEx.take(0).forEach { println(it) } // emptyList()여서 안타는 것처럼 보임 - 에러 안남
        takeEx.take(4).forEach { println(it) } // 123 - 있는만큼만 보여짐
//    takeEx.take(-1).forEach { println(it) } // IllegalArgumentException
    }

    fun toListExercise() {
        // Pair로 변하는 것 주의
        val mapEx: Map<Int, Int> = mapOf(1 to 100, 2 to 200)
        val mapToList: List<Pair<Int, Int>> = mapEx.toList() // Maps의 toList 확장함수.

        val arr: Array<Int> = arrayOf(1, 2, 3)
        val arrToList: List<Int> = arr.toList() // Arrays의 toList 확장함수.

        val arrList: ArrayList<Int> = arrayListOf(1, 2, 3)
        val arrListToList: List<Int> = arrList.toList() // Collections의 toList 확장함수.
    }

    // for 문 돌면서 ArrayList에 addAll(element) - 사실상 for문 2개 도는 셈
    fun flattenExercise() {
        val list3: List<List<List<Int>>> =
            listOf(listOf(listOf(1, 2), listOf(3, 4)), listOf(listOf(5, 6), listOf(7, 8)))
        val list2: List<List<Int>> = list3.flatten()
    }

    // flatMap과 map의 차이 - flatmap은 map을 하고 flatten() 호출 한 것으로 생각하면됨.
    fun flatMapVSMap1() {
        // 아래 3개 예제는 같은 작업을 하는 함수.
        val data: List<String> = listOf("Abcd", "efgh", "Klmn")
        val selected: List<Boolean> = data.map { it.any { c -> c.isUpperCase() } }
        val result: List<Char> =
            data.flatMapIndexed { index, s ->
                if (selected[index]) s.toList() else emptyList()
            }
        println(result) // [A, b, c, d, K, l, m, n]

        val result3 =
            data.mapIndexed { index, s ->
                if (selected[index]) s.toList() else "".toList()
            }.flatten()
        println(result3) // [A, b, c, d, K, l, m, n]

        val result2 =
            data.mapIndexed { index, s ->
                if (selected[index]) s.toList() else emptyList()
            }.reduce { before, after ->
                before + after
            }
        println(result2) // [A, b, c, d, K, l, m, n]
    }

    // flatMap과 map의 차이 - flatmap은 map을 하고 flatten() 호출 한 것으로 생각하면됨.
    fun flatMapVSMap2() {
        val namesArray: Array<Array<String>> = arrayOf(arrayOf("one", "two"), arrayOf("three", "four"))
        Arrays.stream(namesArray)
            .flatMap { inner: Array<String>? ->
                Arrays.stream(inner)
            }
            .filter { name: String -> name == "three" }
            .forEach { x: String? -> println(x) }

        Arrays.stream(namesArray)
            .map { inner: Array<String>? ->
                Arrays.stream(inner)
            }
            .forEach { names: Stream<String> ->
                names.filter { name: String -> name == "three" }
                    .forEach { x: String? -> println(x) }
            }
    }
}