fun main() {
    val answer = Camouflage().solution(
        arrayOf("classic", "pop", "classic", "classic", "pop"),
        intArrayOf(500, 600, 150, 800, 2500)
    )
    answer.forEach { println(it) } // 4,1,3,0
}

class Camouflage {

    fun exercise() {
        val data: List<String> = listOf("Abcd", "efgh", "Klmn")
        val selected: List<Boolean> = data.map { it.any { c -> c.isUpperCase() } }
        val result =
            data.flatMapIndexed { index, s -> if (selected[index]) s.toList() else emptyList() }
        println(result) // [A, b, c, d, K, l, m, n]
    }

    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        val playListMap = mutableMapOf<String, MutableList<Pair<Int, Int>>>()
        genres.mapIndexed { index, genre ->
            if (playListMap.containsKey(genre)) {
                playListMap[genre]?.add(Pair(index, plays[index]))
            } else {
                playListMap[genre] = mutableListOf(Pair(index, plays[index]))
            }
        }
        playListMap.map { (genre, list) ->
            list.sortByDescending { (index, cnt) ->
                cnt
            }
        } // {classic, [(0, 500), (2, 400), (3, 300)]},  {pop, [(1, 500), (4, 300)]} ...

        val totalCntList = playListMap.map { (genre, list) ->
            val totalcnt = list.map { (index, cnt) ->
                cnt
            }.reduce(Int::plus)
            Pair(genre, totalcnt)
        }.sortedByDescending { (genre, totalCnt) ->
            totalCnt
        }

        val answer = mutableListOf<Int>()
        totalCntList.map { (genre, totalCnt) ->
            playListMap[genre]?.getOrNull(0)?.let { (index, cnt) ->
                answer.add(index)
            }
            playListMap[genre]?.getOrNull(1)?.let { (index, cnt) ->
                answer.add(index)
            }
        }

        return answer.toIntArray()
    }
}