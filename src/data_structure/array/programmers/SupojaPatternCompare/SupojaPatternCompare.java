package data_structure.array.programmers.SupojaPatternCompare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupojaPatternCompare {
    
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
