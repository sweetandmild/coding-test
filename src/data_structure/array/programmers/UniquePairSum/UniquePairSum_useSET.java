package data_structure.array.programmers.UniquePairSum;

import java.util.*;


class UniquePairSum_useSET {
    public int[] solution(int[] numbers) {

        // 중복 없이 합을 저장할 Set 사용
        Set<Integer> sumSet = new HashSet<>();

        // 모든 조합의 합을 Set에 저장 (중복 자동 제거됨)
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                sumSet.add(numbers[i] + numbers[j]);
            }
        }

        // Set → 배열로 변환 + 정렬
        return sumSet.stream()
                     .sorted() // 오름차순 정렬
                     .mapToInt(Integer::intValue)
                     .toArray();
    }
}

// 풀이완료 : 13분
