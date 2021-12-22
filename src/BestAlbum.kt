fun main() {
    val answer = BestAlbum().solution(
        arrayOf("classic", "pop", "classic", "classic", "pop"),
        intArrayOf(500, 600, 150, 800, 2500)
    )
    answer.forEach { println(it) } // 4,1,3,0
}

class BestAlbum {
    fun betterSolution(genres: Array<String>, plays: IntArray): IntArray {
        return genres.indices.groupBy { genres[it] }
            .toList()
            .sortedByDescending { it.second.sumBy { plays[it] } }
            .map { it.second.sortedByDescending { plays[it] }.take(2) }
            .flatten()
            .toIntArray()
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

        return totalCntList.flatMap { (genre, totalCnt) ->
            playListMap[genre]?.take(2)?.map { (index, cnt) -> index } ?: emptyList()
        }.toIntArray()
    }

    fun selfBetter(genres: Array<String>, plays: IntArray): IntArray {
        return genres.indices.groupBy { genres[it] } // classic : [0,1,2] pop: [3,4]
            .toList()
            .sortedByDescending { (genre, indexList) -> // 해당 장르 전체 재생 횟수에 따라 DescendingSort
                indexList.sumOf { plays[it] }
            }
            .flatMap { (genre, indexList) -> // 각 장르 내 재생횟수 큰것부터 sort.하는데 그 앞에서 2개만 쓸거고 얘네들을 flat하게 써야함.
                indexList.sortedByDescending { plays[it] }.take(2)
            }
            .toIntArray()
    }
}