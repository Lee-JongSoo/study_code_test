class Solution {
    fun solution(n: Int): Int {
        return if(n % 7 == 0) n / 7 else n / 7 + 1
    }
}