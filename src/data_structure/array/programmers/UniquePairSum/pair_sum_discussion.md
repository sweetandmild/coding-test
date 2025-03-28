# "두 개 뽑아서 더하기" 문제 풀이 과정과 프로그래밍적 통찰 정리

## ✅ 문제 접근 과정 요약
처음에는 배열에서 **서로 다른 두 수를 뽑아 더한 결과들을 구하고**, 그 중 **중복을 제거한 뒤 오름차순으로 정렬**하여 반환하는 문제를 다뤘다.

### 🔹 최초 풀이 코드:
```java
class Solution {
    public int[] solution(int[] numbers) {
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < numbers.length; i++) {
            for(int j = i + 1; j < numbers.length; j++) {
                list.add(numbers[i] + numbers[j]);
            }
        }

        int[] answer = list.stream()
                           .distinct()
                           .sorted()
                           .mapToInt(Integer::intValue)
                           .toArray();
        return answer;
    }
}
```

### 🔹 코드 핵심 요약:
- 모든 서로 다른 두 수의 합을 리스트에 저장
- `stream().distinct()`로 중복 제거
- `sorted()`로 오름차순 정렬
- 최종적으로 `int[]` 배열로 반환

---

## ✅ 성능 최적화 관점에서의 리팩토링

### 🔹 중복 제거와 정렬을 더 효율적으로 처리하기
- `List`로 먼저 데이터를 모은 뒤 `distinct()`로 중복 제거하는 방식은 **데이터가 많을수록 비효율적일 수 있음**
- 처음부터 `Set`을 사용하면 **중복을 실시간으로 제거**할 수 있음

### 🔹 `TreeSet`을 활용하면?
- `TreeSet`은 **중복 제거 + 자동 오름차순 정렬**이 동시에 이뤄짐
- 따라서 별도로 `distinct()`와 `sorted()`를 사용할 필요가 없음

```java
Set<Integer> sumSet = new TreeSet<>();
```

---

## ✅ 성능 벤치마크 실험

### 🔹 세 가지 방식 비교
| 방식                       | 특징                              | 예상 실행 시간 |
|--------------------------|-----------------------------------|----------------|
| List + distinct + sorted | 모든 값을 담은 후 stream으로 처리 | 80~120ms       |
| HashSet + sort           | 중복은 Set에서 제거, 정렬은 stream | 50~80ms        |
| TreeSet                  | 삽입 시 중복 제거 + 자동 정렬      | 30~60ms        |

- 데이터가 많아질수록 TreeSet 방식이 가장 효율적임

---

## ✅ 시간복잡도 분석 및 판단 방법

### 🔹 내 코드의 시간복잡도 분석
```java
for(int i = 0; i < numbers.length; i++) {
    for(int j = i + 1; j < numbers.length; j++) {
        list.add(numbers[i] + numbers[j]);
    }
}
// 총 약 N * (N - 1) / 2 번 반복 → O(N²)

list.stream()
    .distinct() // 중복 제거 (Set 기반) → O(N)
    .sorted()   // 정렬 → O(N log N)
```

➡️ **전체 시간복잡도**:  
**O(N²)** (중첩 반복문) +  
**O(N log N)** (정렬)  
→ 따라서 **최종 시간복잡도는 O(N² log N)**


### 🔹 시간복잡도 판단을 빠르게 하기 위한 팁

1. **반복문 구조 파악**  
   - 단일 반복 → O(N)  
   - 이중 중첩 반복 → O(N²)

2. **정렬, distinct 같은 메서드는 내부 시간복잡도를 기억하기**  
   - `sorted()` → O(N log N)  
   - `distinct()` → O(N) (Set 활용)

3. **자료구조별 연산 시간 알아두기**  
   - `HashSet.add()` → O(1)  
   - `TreeSet.add()` → O(log N)

4. **입력 크기별 시간제한 가이드**
   | 입력 크기 N | 허용 시간복잡도 |
   |-------------|-----------------|
   | N ≤ 10      | O(N!), O(2^N) 가능  |
   | N ≤ 1,000   | O(N²) 가능  |
   | N ≤ 10⁵     | O(N log N) 이하 권장  |
   | N ≥ 10⁶     | O(N), O(log N) 수준 요구  |

---

## ✅ 가장 인상 깊었던 프로그래밍적 통찰



> **"스트림도 내부적으로 자료구조를 쓰니까, 목적에 맞는 자료구조를 먼저 잘 고르는 게 성능적으로 정말 중요하구나!"**

이 문장은 내가 직접 말한 내용으로, 스트림을 사용하는 것이 무조건 옳다기보다는 **데이터를 어떤 자료구조에 어떻게 담느냐가 근본적인 성능에 더 큰 영향을 줄 수 있다**는 걸 깨달았던 순간이었다.

---

## ✅ 최종 요약 및 배운 점
- Stream은 **가독성과 선언형 처리에 유리**하지만, 퍼포먼스가 중요한 상황에서는 **자료구조 선택이 더 중요**할 수 있다.
- 목적에 맞는 자료구조 (`HashSet`, `TreeSet`, `ArrayList` 등)를 **처음부터 잘 선택**하면 불필요한 중복 연산을 줄이고 효율적인 처리 가능
- 문제 풀이뿐 아니라, 데이터 흐름 전체를 설계하는 습관을 가지는 것이 중요함

---

앞으로도 이런 식으로 코드와 개념을 함께 이해하며 정리해나가면 실력이 빠르게 성장할 수 있다. 더 궁금하거나 정리하고 싶은 주제가 있다면 언제든지 이어서 도움을 요청하자!

---

📁 관련 파일 보기: [UniquePairSum_useDISTINCT.java](./UniquePairSum_useDISTINCT.java)
