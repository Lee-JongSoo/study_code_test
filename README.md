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



# Kotlin String Cheatsheet

## 1. 프로퍼티 (Properties)

| 프로퍼티 | 설명 | 예시 |
| :--- | :--- | :--- |
| `length` | 문자열의 길이(문자 개수) 반환 (`size` 대신 사용) | `str.length` |
| `indices` | 유효한 인덱스 범위 (`0..length-1`) 반환 | `for (i in str.indices)` |
| `lastIndex` | 마지막 문자의 인덱스 (`length - 1`) 반환 | `str.lastIndex` |

```kotlin
fun main() {
    val str = "Kotlin"

    println(str.length)     // 6
    println(str.lastIndex)  // 5

    for (i in str.indices) {
        print("$i ")        // 0 1 2 3 4 5
    }
}
```

---

## 2. 값 조회, 탐색 및 검사

| 함수 / 연산자 | 설명 |
| :--- | :--- |
| `[index]`, `get(index)` | 특정 인덱스의 문자(`Char`) 조회 |
| `contains(x)`, `x in str` | 특정 문자열/문자 포함 여부 확인 (`Boolean`) |
| `indexOf(x)` / `lastIndexOf(x)` | 문자(열)가 처음/마지막으로 등장하는 인덱스 (없으면 `-1`) |
| `startsWith(x)` / `endsWith(x)` | 특정 문자열로 시작/끝나는지 확인 (`Boolean`) |
| `isEmpty()` / `isBlank()` | 비어있는지 확인 (`isBlank`는 공백 문자열도 `true`) |

```kotlin
fun main() {
    val str = "Hello Kotlin"

    println(str[1])               // 'e'
    println("Kot" in str)         // true
    println(str.indexOf('o'))     // 4
    println(str.lastIndexOf('o')) // 7
    println(str.startsWith("He")) // true

    val emptyStr = "   "
    println(emptyStr.isEmpty())   // false (공백 문자가 있음)
    println(emptyStr.isBlank())   // true (공백만 있음)
}
```

---

## 3. 문자열 변환 및 가공

| 함수 | 설명 | 반환 타입 |
| :--- | :--- | :--- |
| `substring(range)` | 지정한 범위의 부분 문자열 추출 | `String` |
| `replace(old, new)` | 특정 문자(열)를 다른 문자(열)로 치환 | `String` |
| `trim()` | 앞뒤 공백 제거 | `String` |
| `split(delim)` | 구분자로 나누어 리스트로 반환 | `List<String>` |
| `uppercase()` / `lowercase()` | 대문자 / 소문자 변환 | `String` |

```kotlin
fun main() {
    val str = "  Hello, Kotlin!  "

    println(str.trim())                     // "Hello, Kotlin!"
    println(str.substring(2..6))            // "Hello"
    println(str.replace("Kotlin", "World")) // "  Hello, World!  "
    println(str.lowercase())                // "  hello, kotlin!  "

    val csv = "apple,banana,orange"
    val fruits = csv.split(",")             // ["apple", "banana", "orange"]
    println(fruits[0])                      // apple
}
```

---

## 4. 순서 변경 및 정렬

`String`은 불변(Immutable) 객체이므로 원본을 직접 수정하는 `sort()` 대신 항상 **새로운 문자열이나 배열을 반환**합니다.

| 함수 | 설명 | 반환 타입 |
| :--- | :--- | :--- |
| `reversed()` | 문자열의 순서를 뒤집음 | `String` |
| `toCharArray()` | 문자열을 `CharArray`로 변환 | `CharArray` |
| `toCharArray().sortedArray()` | 문자 단위 오름차순 정렬 | `CharArray` |

```kotlin
fun main() {
    val str = "cadb"

    // 뒤집기
    println(str.reversed()) // "bdac"

    // 문자열 정렬 (CharArray 변환 후 정렬 -> String 재변환)
    val sortedChars = str.toCharArray().apply { sort() }
    val sortedStr = String(sortedChars)
    println(sortedStr) // "abcd"
}
```

---

## 5. 고차 함수 및 순회

| 함수 | 설명 | 반환 타입 |
| :--- | :--- | :--- |
| `map { ... }` | 각 문자를 변환 | `List<R>` |
| `filter { ... }` | 조건에 맞는 문자만 추출하여 새 문자열 생성 | `String` |
| `forEach { ... }` | 각 문자 순회 | `Unit` |
| `forEachIndexed { i, c -> ... }` | 인덱스와 문자를 함께 순회 | `Unit` |
| `count { ... }` | 조건에 맞는 문자의 개수 반환 | `Int` |

```kotlin
fun main() {
    val str = "a1b2c3d4"

    // 숫자만 추출하여 새 문자열 생성
    val digitsOnly = str.filter { it.isDigit() }
    println(digitsOnly) // "1234"

    // 조건 충족 개수
    val digitCount = str.count { it.isDigit() }
    println(digitCount) // 4

    // 순회
    str.forEachIndexed { index, char ->
        if (char.isLetter()) {
            print("[$index]: $char ") // [0]: a [2]: b [4]: c [6]: d 
        }
    }
}
```
