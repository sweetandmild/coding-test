package data_structure.array.programmers.UniquePairSum;

import java.util.*;

class UniquePairSum_useTREESET {
    public int[] solution(int[] numbers) {

        // TreeSet은 자동으로 정렬 + 중복 제거를 해줌
        Set<Integer> sumSet = new TreeSet<>();

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                sumSet.add(numbers[i] + numbers[j]);
            }
        }

        // Set → 배열로 변환
        return sumSet.stream()
                     .mapToInt(Integer::intValue)
                     .toArray();
    }
}

// 풀이완료 : 13분
