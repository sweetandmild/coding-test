# 수포자 문제 리팩토링

## ▶ 문제 설명

3명의 수포자가 일정한 패턴으로 문제를 찍습니다. `answers`라는 배열에 실제 정답이 주어질 때, 가장 많은 문제를 맞힌 사람의 번호를 오름차순으로 반환하는 문제입니다.

---


## ▶ 기존 구현 (원본 코드)
📁 관련 파일 보기: [SupojaPatternCompare.java](./SupojaPatternCompare.java)
```java
import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] student1 = {1,2,3,4,5};
        int[] student2 = {2,1,2,3,2,4,2,5};
        int[] student3 = {3,3,1,1,2,2,4,4,5,5};

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,0);
        map.put(2,0);
        map.put(3,0);

        for(int i=0; i < answers.length; i++) {
            if(answers[i] == student1[i%student1.length]) map.put(1, map.get(1)+1);
            if(answers[i] == student2[i%student2.length]) map.put(2, map.get(2)+1);
            if(answers[i] == student3[i%student3.length]) map.put(3, map.get(3)+1);
        }

        List<Integer> rank = new ArrayList<>();
        int max = 0;

        for(Integer key : map.keySet()){
            if(map.get(key) > max) {
                max = map.get(key);
                rank.clear();
                rank.add(key);
            } else if(map.get(key) == max){
                rank.add(key);
            }
        }

        return rank.stream().sorted().mapToInt(Integer::intValue).toArray();
    }
}
```

---

## ▶ 리팩토링 후 개선된 코드
📁 관련 파일 보기: [SupojaPatternCompareRefactoring.java](./SupojaPatternCompareRefactoring.java)
```java
import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] student1 = {1, 2, 3, 4, 5};
        int[] student2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] student3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int[] scores = new int[3]; // 0: 수포자1, 1: 수포자2, 2: 수포자3

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == student1[i % student1.length]) scores[0]++;
            if (answers[i] == student2[i % student2.length]) scores[1]++;
            if (answers[i] == student3[i % student3.length]) scores[2]++;
        }

        int max = Math.max(scores[0], Math.max(scores[1], scores[2]));

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (scores[i] == max) result.add(i + 1); // 수포자 번호는 1부터 시작
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}
```

---

## ▶ 수포자 수가 유동적인 경우 (확장된 설계)

문제 정의가 다음과 같이 확장될 수 있습니다:

> `answers`와 `int[][] patterns` (각 수포자의 찍기 패턴 배열)을 인자로 받아,
> 가장 많은 문제를 맞힌 수포자의 번호(1번부터 시작)를 오름차순으로 반환하라.

### 리팩토링 코드 (수포자 수 동적 대응)
📁 관련 파일 보기: [GeneralizedSupojaGrader.java](./GeneralizedSupojaGrader.java)
```java
import java.util.*;

class Solution {
    public int[] solution(int[] answers, int[][] patterns) {
        int[] scores = new int[patterns.length];

        for (int i = 0; i < answers.length; i++) {
            for (int j = 0; j < patterns.length; j++) {
                int[] pattern = patterns[j];
                if (answers[i] == pattern[i % pattern.length]) {
                    scores[j]++;
                }
            }
        }

        int max = Arrays.stream(scores).max().getAsInt();

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == max) {
                result.add(i + 1);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}
```

이 방식은 수포자의 수가 많아져도 유연하게 대응할 수 있으며, 테스트 케이스마다 수포자 패턴을 유동적으로 바꿀 수 있습니다.

---

## ▶ 리팩토링 후 개선점 및 성능 고려

1. 수포자 수가 고정되어 있어 `Map`보다는 `int[]` 배열이 간단하고 성능도 뛰어남
2. `Map.get()`, `Map.put()` 호출은 배열 인덱스 접근보다 느림
3. 리스트를 생성하고 `clear()`, `add()` 등을 반복하는 과정에서 불필요한 연산 발생 → GC(가비지 컬렉션) 부담 증가 가능성

---

## ▶ 실제 성능 비교 (입력 크기: 10,000 문제)
- 원본 코드 (Map 기반): **4.8988 ms**
- 리팩토링 코드 (배열 기반): **3.2513 ms**

입력 크기가 커질수록 성능 차이는 더 뚜렷하게 나타날 수 있음

---

## ▶ 결론
- 수가 정해진 경우에는 `int[]` 배열을 사용하는 것이 구조적으로 간단하고 실행 성능도 더 좋음
- 불필요한 자료구조 사용과 연산을 줄이면 코드가 더 깔끔하고 효율적임
- 반복 패턴이 명확한 문제일수록 간결한 코드가 유지보수와 가독성 측면에서도 유리함
- 수포자 수가 많아지거나 유동적인 경우에는 `int[][] patterns` 방식으로 유연하게 처리하는 것이 이상적임