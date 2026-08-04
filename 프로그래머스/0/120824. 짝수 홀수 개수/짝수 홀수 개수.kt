class Solution {
    fun solution(list: IntArray): IntArray {
        val a = list.count { it % 2 == 0}
        val b = list.count { it % 2 != 0}
        return intArrayOf(a, b)
    }
}