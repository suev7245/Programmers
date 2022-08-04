fun main() {
    val solutionClass = Two()
    val answer = solutionClass.solution(arrayOf("02/05 15000 2\",\"03/11 5541 1\",\"03/31 10000 3\",\"03/31 100 1\",\"05/15 10000 2\",\"10/10 12345 1\",\"11/22 5999 4\",\"12/01 901 10\""))
    println(answer)
}

class Two {
    fun solution(card: Array<String>): IntArray {
        var point = 0
        val bill = IntArray(13)

        card.forEach {
            val (oriMonth, oriMoney, oriLong) = it.split(" ")
            val month = oriMonth.split("/")[0].toInt()
            val money = oriMoney.toInt()
            val long = oriLong.toInt()

            if (long == 1 || month == 12) point += countPoint(money)

            if (month == 12) bill[12] += money
            else count(month, money, long, bill)
        }

        bill[0] = point
        bill.forEach {
            println(it)
        }

        return bill
    }

    fun count(month: Int, money: Int, long: Int, bill: IntArray) {
        var payPerMonth = money / long
        if (money % long > 0) payPerMonth += 1

        for (i in 0 until long) {
            if (month + i > 12) bill[12] += payPerMonth
            else bill[month + i] += payPerMonth
        }
    }

    fun countPoint(money: Int): Int {
        var point = money / 100
        if (money % 100 > 0) point += 1
        return point
    }
}