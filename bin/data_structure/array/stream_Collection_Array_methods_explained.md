## Java 배열 처리 문제 - 메서드 및 개념 정리

다음은 배열에서 중복을 제거하고 내림차순으로 정렬하는 문제를 해결하면서 사용된 Java의 주요 메서드 및 개념에 대한 정리입니다.

---

## Java Stream 개요

### Stream이란?
- **정의:** Java Stream은 Java 8에서 도입된 기능으로, 컬렉션 또는 배열의 요소들을 함수형 스타일로 처리할 수 있는 추상화된 데이터 처리 도구입니다.

### 왜 만들어졌는가?
- 반복문 중심의 코드에서 발생하는 **복잡성과 오류 가능성**을 줄이고,
- **선언적 프로그래밍(무엇을 할 것인지)** 방식으로 데이터를 처리하여 **코드 가독성과 유지보수성**을 높이기 위해 만들어졌습니다.

### Stream의 핵심 역할
- **데이터 소스(배열, 컬렉션 등)** 로부터 **파이프라인 방식으로 연산**을 수행
- 중간 연산(`filter`, `map`, `sorted` 등)과 최종 연산(`collect`, `forEach`, `toArray` 등)을 조합해 가공
- **원본 데이터를 변경하지 않고 처리**하며, **지연 연산(lazy evaluation)** 을 통해 성능 최적화

### 개발 관점에서의 장점
- **간결함:** for/if 블록 없이 직관적으로 데이터 흐름 표현 가능
- **가독성:** 데이터 변환 과정을 체이닝 방식으로 나열 → 코드 이해가 쉬움
- **병렬 처리 지원:** `.parallelStream()`을 사용하면 멀티코어 활용 가능
- **함수형 프로그래밍 스타일:** 람다식과 조합하여 함수형 스타일의 코드 작성 가능
- **부수 효과 없음:** 원본 데이터를 건드리지 않아 예측 가능하고 안전한 코드 작성 가능

예를 들어, 배열에서 중복 제거 + 정렬 + 변환을 동시에 다음과 같이 작성할 수 있습니다:
```java
int[] result = Arrays.stream(arr)
                     .distinct()
                     .boxed()
                     .sorted(Comparator.reverseOrder())
                     .mapToInt(Integer::intValue)
                     .toArray();
```

---

### 1. `Arrays.stream(arr)`

- **기능:** 기본형 배열(int[])을 스트림으로 변환합니다.
- **설명:** `Stream`은 Java 8에서 도입된 기능으로, 컬렉션/배열의 데이터를 일관된 방식으로 처리할 수 있게 합니다.
- **예시:**
  ```java
  int[] arr = {1, 2, 3};
  IntStream stream = Arrays.stream(arr);
  ```

---

### 2. `.boxed()`

- **기능:** `IntStream` (기본형 int 전용 스트림)을 `Stream<Integer>`로 변환합니다.
- **이유:** `List`나 `Set` 등은 객체 타입(`Integer`)을 사용하기 때문에 변환이 필요합니다.
- **예시:**
  ```java
  Stream<Integer> stream = Arrays.stream(arr).boxed();
  ```

---

### 3. `.distinct()`

- **기능:** 중복된 요소를 제거합니다.
- **특징:** 순서를 유지하며 처음 등장한 값만 남깁니다.
- **예시:**
  ```java
  int[] result = Arrays.stream(arr).distinct().toArray();
  ```

---

### 4. `.sorted(Comparator.reverseOrder())`

- **기능:** 정렬을 수행하는데, 내림차순 정렬을 위해 Comparator 사용
- **주의:** `.sorted()`를 기본으로 쓰면 오름차순, 객체형(`Stream<Integer>`)이어야 `Comparator` 사용 가능
- **예시:**
  ```java
  Stream<Integer> sorted = stream.sorted(Comparator.reverseOrder());
  ```

---

### 5. `.mapToInt(Integer::intValue)`

- **기능:** `Stream<Integer>`를 `IntStream`으로 변환
- **이유:** `toArray()`를 호출할 때 `int[]`로 받기 위해 필요
- **예시:**
  ```java
  int[] result = list.stream().mapToInt(Integer::intValue).toArray();
  ```

---

### 6. `.toArray()`

- **기능:** 최종적으로 스트림의 데이터를 배열로 변환
- **예시:**
  ```java
  int[] finalArr = stream.mapToInt(Integer::intValue).toArray();
  ```

---

### 7. `Set<Integer> set = new HashSet<>(...)`

- **기능:** 중복 제거를 위한 자료구조 사용
- **특징:** 중복을 허용하지 않음, 정렬 순서를 보장하지 않음
- **예시:**
  ```java
  Set<Integer> set = new HashSet<>(List.of(1, 2, 2, 3)); // set = [1, 2, 3]
  ```

---

### 8. `.collect(Collectors.toList())`

- **기능:** 스트림을 리스트로 변환
- **예시:**
  ```java
  List<Integer> list = stream.collect(Collectors.toList());
  ```

---

## 스트림 처리 흐름 요약 도식 (텍스트 기반)

```
[ int[] arr = {4, 2, 1, 3, 2} ]
             |
          stream()
             ↓
   [ 4, 2, 1, 3, 2 ] (IntStream)
             |
           boxed()
             ↓
   [ 4, 2, 1, 3, 2 ] (Stream<Integer>)
             |
         distinct()
             ↓
       [ 4, 2, 1, 3 ]
             |
  sorted(Comparator.reverseOrder())
             ↓
       [ 4, 3, 2, 1 ]
             |
    mapToInt(Integer::intValue)
             ↓
       int[] {4, 3, 2, 1}
```

이러한 스트림과 컬렉션 관련 메서드를 적절히 조합하면, 코드의 가독성과 간결함을 동시에 잡을 수 있습니다. 익숙해지면 많은 데이터를 다룰 때 매우 강력한 도구가 됩니다.
