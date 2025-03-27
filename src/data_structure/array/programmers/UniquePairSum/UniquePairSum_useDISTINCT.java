package data_structure.array.programmers.UniquePairSum;

import java.util.ArrayList;
import java.util.List;

class UniquePairSum_useDISTINCT {
    public int[] solution(int[] numbers) {
        
        // 두 수의 합을 저장할 리스트 (중복 제거 전)
        List<Integer> list = new ArrayList<>();
         
        // 배열의 모든 두 수를 조합해서 합을 구함
        for(int i = 0; i < numbers.length; i++) {
            for(int j = i + 1; j < numbers.length; j++) {
                // i번째와 j번째 수의 합을 리스트에 추가
                list.add(numbers[i] + numbers[j]);
            }
        }

        // 중복 제거(distinct), 정렬(sorted), int 배열로 변환(mapToInt + toArray)
        int[] answer = list.stream()
                           .distinct()                 // 중복 제거
                           .sorted()                   // 오름차순 정렬
                           .mapToInt(Integer::intValue) // Integer -> int 변환
                           .toArray();                 // 최종적으로 배열로 변환

        return answer;
    }
}

// 풀이완료(나의 풀이) : 13분
