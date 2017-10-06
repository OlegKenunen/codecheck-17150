package fw;

import ai.AIgamer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Oleg on 06.10.2017.
 * oleg.kenunen@gmail.com
 */
public class SWCgame {
    public static void main(String[] args) {
//        for (int i = 0, l = args.length; i < l; i++) {
//            System.out.println(args[i]);
//        }

        if (args.length < 3) {
            System.out.println("not enough values");
        }

        AIgamer[] aIgamers = new AIgamer[2];
        aIgamers[0] = new AIgamer(args[0]);
        aIgamers[1] = new AIgamer(args[1]);


        String currentWord = args[2];

        List<String> remainWords = new ArrayList<>();
        if (args.length > 4) {
            remainWords.addAll(Arrays.asList(args).subList(3, args.length));//from index to index
        }
        System.out.println("initial word: " + currentWord);
        System.out.println("available words: " + remainWords);
        for (int i = remainWords.size() / 2 + 1; i >= 0; i--) {
            for (int g = 0; g < 2; g++) {
//                System.out.println("i= " + i + " g= "+ g);
//                System.out.println("initial word: "+ currentWord);
//                System.out.println("available words: "+ remainWords);
                String currentGamerAnswer = aIgamers[g].play(currentWord, remainWords);
                ;

                if (currentWord.charAt(currentWord.length() - 1) == currentGamerAnswer.charAt(0)) {
                    System.out.println(aIgamers[g].getName() + " (OK): " + currentGamerAnswer);

                    currentWord = currentGamerAnswer;
                    for (int w = 0; w < remainWords.size(); w++) {
                        if (remainWords.get(w).equals(currentGamerAnswer)) {
                            remainWords.remove(w);
                        }
                    }
                } else {
                    System.out.println(aIgamers[g].getName() + " (NG): " + currentGamerAnswer);
                    System.out.println(aIgamers[1 - g].getName() + " Wins");
                    return;
                }
            }
        }
        System.out.println("Standoff: words finished");
    }


}
