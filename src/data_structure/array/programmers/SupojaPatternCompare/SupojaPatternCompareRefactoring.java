package data_structure.array.programmers.SupojaPatternCompare;

import java.util.*;

public class SupojaPatternCompareRefactoring {

    public int[] solution(int[] answers) {
        int[] student1 = {1,2,3,4,5};
        int[] student2 = {2,1,2,3,2,4,2,5};
        int[] student3 = {3,3,1,1,2,2,4,4,5,5};

        int[] scores = new int[3];


        for(int i=0; i < answers.length; i++) {
            if(answers[i] == student1[i%student1.length]) scores[0]++;
            if(answers[i] == student2[i%student2.length]) scores[1]++;
            if(answers[i] == student3[i%student3.length]) scores[2]++;
        }

        int max = Math.max(scores[0], Math.max(scores[1], scores[2]));
        List<Integer> rank = new ArrayList<>();

        for(int i=0; i<scores.length; i++){
            if(max == scores[i]) rank.add(i+1); 
        }


        return rank.stream().mapToInt(Integer::intValue).toArray();
    }
}
