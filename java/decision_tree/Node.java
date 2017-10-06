package decision_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleg on 06.10.2017.
 * oleg.kenunen@gmail.com
 * Node of Decision Tree
 *
 */
public class Node {
    private List<Node> children = new ArrayList<>();
    private String word = null;
    private int depth;
    private int worstGain; //main characteristics to find Nash equilibrium in sub-games
    private double meanGain; //additional characteristics for the case of several good choices

    private int getWorstGain() {
        return worstGain;
    }

    private double getMeanGain() {
        return meanGain;
    }

    public Node(String parentWord, List<String> otherWords, int depth) {

//        System.out.println("depth: " + depth + " parent word: " + parentWord);
//        System.out.println("otherWords " + otherWords);
        this.word = parentWord;
        this.depth = depth;

        char lastLetter = this.word.charAt(this.word.length() - 1);

        for (int i = 0; i < otherWords.size(); i++) {
//            System.out.println("parent word: " + parentWord + " lastLetter: " + lastLetter +
//                    " next word: " + otherWords.get(i) + " firstLetter: " + otherWords.get(i).charAt(0));

            if (otherWords.get(i).charAt(0) == lastLetter) {
//                System.out.println(this.word + " > " + otherWords.get(i));
                List<String> childListOfWords = new ArrayList<>(otherWords);
                childListOfWords.remove(i);
                children.add(new Node(otherWords.get(i), childListOfWords, this.depth + 1));
            }
        }

        if (children.size() == 0) {
            if (depth % 2 != 0) {
                this.worstGain = 1;
                this.meanGain = 1;
            } else {
                this.worstGain = -1;
                this.meanGain = -1;
            }
        } else {
            this.worstGain = 1;
            for (Node aChildren : children) {
                this.worstGain = Math.min(this.worstGain, aChildren.getWorstGain());
                this.meanGain = (this.meanGain + aChildren.getMeanGain()) / 2;
            }
        }
//        System.out.println("depth: " + depth +
//                " word: " + this.word +
//                " First gain: " + getCurrentGamerWorstGain() +
//                " Second gain: " + getSecondGamerWorstGain()
//        );


    }

    private String getWord() {
        return word;
    }

    public String findSolution() {
        if (children.size() == 0) {
            return "#none";
        }

        int numWinningWorst = 0;

        //find bed solutions
        for (Node aChildren : children) {
            //System.out.println(children.get(i).getWord() + " worst " + children.get(i).worstGain + " mean " + children.get(i).getMeanGain());
            if (aChildren.worstGain == 1) {
                numWinningWorst += 1;
            }
        }

        //delete bad solutions
        if (numWinningWorst > 0) {
            for (int i = children.size()-1; i >=0 ; i--){
                if(children.get(i).worstGain==-1){
                    children.remove(i);
                }
            }
        }

        //find best solution
        double maxMeanGain=-1;
        int maxMeanIndex=0;
        for (int i = 0; i < children.size(); i++) {
            //System.out.println(children.get(i).getWord() + " worst " + children.get(i).worstGain + " mean " + children.get(i).getMeanGain());
            if (children.get(i).getMeanGain() > maxMeanGain) {
                maxMeanGain=children.get(i).getMeanGain();
                maxMeanIndex=i;
            }
        }


        return children.get(maxMeanIndex).getWord();
    }


}
