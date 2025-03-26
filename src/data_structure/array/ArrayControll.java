package data_structure.array;

import java.util.*;

public class ArrayControll {

    /**
     * 방법 1: Set을 이용해 중복 제거 후 내림차순 정렬
     */
    public static int[] solutionUsingSet(int[] arr) {

        // 1. 배열을 Set으로 변환하여 중복 제거
        Set<Integer> set = new HashSet<>(Arrays.stream(arr).boxed().toList());

        // 2. Set을 List로 변환 후 내림차순 정렬
        List<Integer> list = new ArrayList<>(set);
        list.sort(Comparator.reverseOrder());

        // 3. List를 다시 int[]로 변환하여 반환
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 방법 2: stream().distinct()를 이용해 중복 제거 후 내림차순 정렬
     */
    public static int[] solutionUsingDistinct(int[] arr) {

        // 1. 배열을 스트림으로 변환하고 distinct()로 중복 제거
        // 2. 내림차순 정렬
        // 3. int[]로 변환하여 반환
        return Arrays.stream(arr)
                     .distinct()
                     .boxed()
                     .sorted(Comparator.reverseOrder())
                     .mapToInt(Integer::intValue)
                     .toArray();
    }

}
