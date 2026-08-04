Kotlin IntArray 완전 정리 가이드
1. 프로퍼티 (Properties)
배열의 상태나 인덱스 정보를 조회할 때 사용합니다.

Kotlin
fun main() {
    val arr = intArrayOf(10, 20, 30, 40)

    // size: 배열의 크기
    println(arr.size) // 4

    // indices: 유효 인덱스 범위 (IntRange)
    for (i in arr.indices) {
        print("$i ") // 0 1 2 3
    }
    println()

    // lastIndex: 마지막 원소의 인덱스 (size - 1)
    println(arr.lastIndex) // 3
}
2. 값 조회 및 탐색
원소에 접근하거나 특정 값의 위치 및 포함 여부를 확인합니다.

Kotlin
fun main() {
    val arr = intArrayOf(10, 20, 30, 40, 20)

    // [index] / get(index): 조회
    println(arr[1]) // 20
    println(arr.get(1)) // 20

    // [index] = value / set(index, value): 값 변경
    arr[0] = 99
    println(arr[0]) // 99

    // contains / in: 값 포함 여부
    println(arr.contains(30)) // true
    println(30 in arr) // true

    // indexOf: 값의 첫 번째 인덱스 찾기 (없으면 -1)
    println(arr.indexOf(20)) // 1
    println(arr.indexOf(100)) // -1

    // binarySearch: 이진 탐색 (반드시 정렬된 상태여야 함)
    val sortedArr = intArrayOf(10, 20, 30, 40, 50)
    println(sortedArr.binarySearch(30)) // 2
}
3. 집계 및 연산 함수
배열 내 데이터의 합계, 평균, 최댓값, 개수 등을 계산합니다.

Kotlin
fun main() {
    val arr = intArrayOf(10, 20, 30, 40, 50)

    // sum(): 모든 원소의 합계
    println(arr.sum()) // 150

    // average(): 평균값 (Double 타입 반환)
    println(arr.average()) // 30.0

    // maxOrNull() / minOrNull(): 최댓값 / 최솟값 (빈 배열이면 null)
    println(arr.maxOrNull()) // 50
    println(arr.minOrNull()) // 10

    // count(): 전체 개수 또는 조건에 맞는 개수
    println(arr.count()) // 5
    println(arr.count { it >= 30 }) // 3 (30 이상인 원소 개수)
}
4. 정렬 및 순서 변경
원소의 순서를 바꾸거나 정렬할 때 사용합니다. 원본 변형(In-place) 방식과 새 배열 반환 방식의 차이를 주의해야 합니다.

Kotlin
fun main() {
    // 1. In-place 변경 (원본 배열 자체가 변경됨)
    val arr1 = intArrayOf(3, 1, 4, 2)
    arr1.sort() // 오름차순 정렬
    println(arr1.contentToString()) // [1, 2, 3, 4]

    arr1.sortDescending() // 내림차순 정렬
    println(arr1.contentToString()) // [4, 3, 2, 1]

    val arr2 = intArrayOf(1, 2, 3, 4)
    arr2.reverse() // 원본 배열 뒤집기
    println(arr2.contentToString()) // [4, 3, 2, 1]

    // 2. 새로운 배열 반환 (원본 배열은 유지됨)
    val original = intArrayOf(3, 1, 4, 2)

    val sorted = original.sortedArray() // 정렬된 새 IntArray 생성
    println("sorted: ${sorted.contentToString()}") // [1, 2, 3, 4]
    println("original: ${original.contentToString()}") // [3, 1, 4, 2] (원본 유지)

    val reversed = original.reversedArray() // 뒤집힌 새 IntArray 생성
    println("reversed: ${reversed.contentToString()}") // [2, 4, 1, 3]
    println("original: ${original.contentToString()}") // [3, 1, 4, 2] (원본 유지)
}
5. 고차 함수 및 변환
배열 순회, 조건 가공, 타 데이터 타입 변환을 처리합니다.

Kotlin
fun main() {
    val arr = intArrayOf(1, 2, 3, 4, 5)

    // map & filter: 원소 변환 및 추출 (반환 타입: List<Int>)
    val doubledList: List<Int> = arr.map { it * 2 }
    val evenList: List<Int> = arr.filter { it % 2 == 0 }
    println(doubledList) // [2, 4, 6, 8, 10]
    println(evenList) // [2, 4]

    // forEach: 각 원소 순회
    arr.forEach { num ->
        print("$num ") // 1 2 3 4 5
    }
    println()

    // forEachIndexed: 인덱스와 원소를 함께 순회
    arr.forEachIndexed { index, value ->
        println("인덱스 $index: 값 $value")
    }

    // 컬렉션 변환
    val list: List<Int> = arr.toList()
    val set: Set<Int> = arr.toSet()

    // joinToString: 구분자로 문자열 이어붙이기
    val resultStr = arr.joinToString(separator = ", ", prefix = "[", postfix = "]")
    println(resultStr) // [1, 2, 3, 4, 5]
}
