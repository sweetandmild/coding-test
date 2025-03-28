package data_structure.array.programmers.SupojaPatternCompare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneralizedSupojaGrader {

    public int[] solution(int[] answers, int[][] patterns) {

        int[] scores = new int[patterns.length];

        for(int i=0; i < answers.length; i++){
            for(int j=0; j < patterns.length; i++){
                if(answers[i] == patterns[j][i%patterns[i].length]) scores[i]++;
            }
        }

        int max = Arrays.stream(scores).max().getAsInt();

        List<Integer> rank = new ArrayList<>();

        for(int i=0; i < scores.length; i++) {
            if(max == scores[i]) rank.add(i+1);
        }

        return rank.stream().mapToInt(Integer::intValue).toArray();
    }
}
