import kotlin.math.ceil

fun main() {
    val solutionClass = Fee()
    val fees = intArrayOf(120, 0, 60, 591)
    val records = arrayOf(
        "16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN"
    )
    solutionClass.solution3(fees, records).map {
        println(it)
    }
}

class Fee {

    fun solution(fees: IntArray, records: Array<String>): IntArray {
        val before = mutableMapOf<String, String>()
        val timeSum = mutableMapOf<String, Int>()

        records.forEach { it ->
            val (time, car, _) = it.split(" ")
            before[car]?.let { beforeTime ->
                timeSum[car] = getBetweenTime(beforeTime, time) + timeSum.getOrDefault(car, 0)
                before.remove(car)
            } ?: run {
                before[car] = time
            }
        }

        // 출차 안된 케이스
        before.forEach { (car, time) ->
            timeSum[car] = getBetweenTime(time, "23:59") + (timeSum[car] ?: 0)
        }

        return timeSum.map { (car, time) ->
            car to fees[1] + ceil((time - fees[0]).coerceAtLeast(0) / fees[2].toDouble()).toInt() * fees[3]
        }
            .sortedBy { it.first }
            .map { it.second }
            .toIntArray()
    }

    private fun getBetweenTime(before: String, now: String): Int {
//        val (beforeHour, beforeMin) = before.split(":").map { it.toInt() }
//        var (nowHour, nowMin) = now.split(":").map { it.toInt() }
//
//        if (nowMin < beforeMin) {
//            nowHour -= 1
//            nowMin += 60
//        }
//
//        return nowMin - beforeMin + (nowHour - beforeHour) * 60

        val (beforeHour, beforeMin) = before.split(":").map { it.toInt() }
        val (nowHour, nowMin) = now.split(":").map { it.toInt() }

        return nowHour * 60 + nowMin - (beforeHour * 60 + beforeMin)
    }

    fun solution2(fees: IntArray, records: Array<String>): IntArray {
        return records
            .map { it.split(' ') }
            .map { (time, car, what) ->
                var convertedTime = time.split(':').let { it[0].toInt() * 60 + it[1].toInt() }
                if (what == "IN") convertedTime = -convertedTime
                Pair(car, convertedTime)
            }
            .groupBy { it.first }
            .map { (k, infos) ->
                var acc = infos.sumOf { it.second }
                if (infos.size % 2 == 1) acc += 23 * 60 + 59
                k to fees[1] + ceil((acc - fees[0]).coerceAtLeast(0) / fees[2].toDouble()).toInt() * fees[3]
            }
            .sortedBy { it.first }
            .map { it.second }
            .toIntArray()
    }

    fun solution3(fees: IntArray, records: Array<String>): IntArray {
        return records
            .map { it.split(" ") }
            .map { (time, car, what) ->
                car to if (what == "IN") getMinutes(time).unaryMinus() else getMinutes(time)
            }.groupBy { it.first }
            .map { (car, list) ->
                var totalTime = list.sumOf { it.second }
                if (list.size % 2 == 1) totalTime += 23 * 60 + 59
                car to getFee(totalTime, fees)
            }.sortedBy { it.first }
            .map { it.second }
            .toIntArray()
    }

    fun getMinutes(time: String): Int {
        val (hour, min) = time.split(":").map { it.toInt() }
        return hour * 60 + min
    }

    fun getFee(totalTime: Int, fees: IntArray): Int {
        return fees[1] + ceil((totalTime - fees[0]).coerceAtLeast(0) / fees[2].toDouble()).toInt() * fees[3]
    }
}