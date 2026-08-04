# Kotlin IntArray Cheatsheet

## 1. 프로퍼티 (Properties)

| 프로퍼티 | 설명 | 예시 |
| :--- | :--- | :--- |
| `size` | 배열의 크기(원소 개수) 반환 | `arr.size` |
| `indices` | 유효한 인덱스 범위 (`0..size-1`) 반환 | `for (i in arr.indices)` |
| `lastIndex` | 마지막 원소의 인덱스 (`size - 1`) 반환 | `arr.lastIndex` |

```kotlin
fun main() {
    val arr = intArrayOf(10, 20, 30, 40)

    println(arr.size)       // 4
    println(arr.lastIndex)  // 3

    for (i in arr.indices) {
        print("$i ")        // 0 1 2 3
    }
}
```

---

## 2. 값 조회 및 탐색

| 함수 / 연산자 | 설명 |
| :--- | :--- |
| `[index]`, `get(index)` | 특정 인덱스의 값 조회 |
| `[index] = value`, `set(index, value)` | 특정 인덱스의 값 변경 |
| `contains(x)`, `x in arr` | 특정 값 포함 여부 확인 (`Boolean`) |
| `indexOf(x)` | 값이 처음 등장하는 인덱스 반환 (없으면 `-1`) |
| `binarySearch(x)` | 이진 탐색으로 인덱스 반환 (**오름차순 정렬 필수**) |

```kotlin
fun main() {
    val arr = intArrayOf(10, 20, 30, 40, 20)

    // 조회 및 수정
    println(arr[1])         // 20
    arr[0] = 99
    println(arr[0])         // 99

    // 포함 여부 및 탐색
    println(30 in arr)      // true
    println(arr.indexOf(20))// 1 (첫 번째 위치)

    // 이진 탐색 (정렬 필수)
    val sortedArr = intArrayOf(10, 20, 30, 40, 50)
    println(sortedArr.binarySearch(30)) // 2
}
```

---

## 3. 집계 및 연산 함수

| 함수 | 설명 | 반환 타입 |
| :--- | :--- | :--- |
| `sum()` | 모든 원소의 합계 | `Int` |
| `average()` | 모든 원소의 평균값 | `Double` |
| `maxOrNull()`, `minOrNull()` | 최댓값 / 최솟값 반환 (빈 배열 시 `null`) | `Int?` |
| `count()` | 전체 개수 또는 조건 만족 개수 반환 | `Int` |

```kotlin
fun main() {
    val arr = intArrayOf(10, 20, 30, 40, 50)

    println(arr.sum())              // 150
    println(arr.average())          // 30.0
    println(arr.maxOrNull())        // 50
    println(arr.minOrNull())        // 10
    println(arr.count { it >= 30 }) // 3
}
```

---

## 4. 정렬 및 순서 변경

| 구분 | 함수 | 설명 |
| :--- | :--- | :--- |
| **In-place (원본 변경)** | `sort()` | 오름차순 정렬 |
| | `sortDescending()` | 내림차순 정렬 |
| | `reverse()` | 배열 순서 뒤집기 |
| **New Array (새 배열 반환)** | `sortedArray()` | 오름차순 정렬된 새 `IntArray` 반환 |
| | `reversedArray()` | 순서가 뒤집힌 새 `IntArray` 반환 |

```kotlin
fun main() {
    // 1. In-place (원본 변형)
    val arr1 = intArrayOf(3, 1, 4, 2)
    arr1.sort()
    println(arr1.contentToString()) // [1, 2, 3, 4]

    arr1.reverse()
    println(arr1.contentToString()) // [4, 3, 2, 1]

    // 2. 새 배열 반환 (원본 유지)
    val original = intArrayOf(3, 1, 4, 2)
    val sorted = original.sortedArray()
    val reversed = original.reversedArray()

    println(sorted.contentToString())   // [1, 2, 3, 4]
    println(reversed.contentToString()) // [2, 4, 1, 3]
    println(original.contentToString()) // [3, 1, 4, 2] (원본 그대로)
}
```

---

## 5. 고차 함수 및 변환

| 함수 | 설명 | 반환 타입 |
| :--- | :--- | :--- |
| `map { ... }` | 각 원소를 변환 | `List<R>` |
| `filter { ... }` | 조건에 맞는 원소만 추출 | `List<Int>` |
| `forEach { ... }` | 각 원소 순회 | `Unit` |
| `forEachIndexed { i, v -> ... }` | 인덱스와 원소를 함께 순회 | `Unit` |
| `toList()`, `toSet()` | List / Set 컬렉션으로 변환 | `List<Int>`, `Set<Int>` |
| `joinToString(...)` | 구분자로 이어진 문자열 생성 | `String` |

```kotlin
fun main() {
    val arr = intArrayOf(1, 2, 3, 4, 5)

    // 가공 및 추출 (List 반환)
    val doubled = arr.map { it * 2 }       // [2, 4, 6, 8, 10]
    val evens = arr.filter { it % 2 == 0 } // [2, 4]

    // 순회
    arr.forEachIndexed { index, value ->
        println("[$index]: $value")
    }

    // 컬렉션 및 문자열 변환
    val list = arr.toList()
    val str = arr.joinToString(separator = ", ", prefix = "[", postfix = "]")
    println(str) // [1, 2, 3, 4, 5]
}
```
