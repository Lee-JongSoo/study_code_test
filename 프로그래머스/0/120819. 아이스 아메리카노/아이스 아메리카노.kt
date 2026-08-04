class Solution {
    fun solution(money: Int): IntArray {
        val count = money / 5500
        val rest = money - (count * 5500)
        return intArrayOf(count, rest)
    }
}