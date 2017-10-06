package ai;

import decision_tree.Node;

import java.util.List;

/**
 * Created by Oleg on 06.10.2017.
 * oleg.kenunen@gmail.com
 */
public class AIgamer {
    private String name;

    public AIgamer(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public String play(String currentWord, List<String> otherWords){

        //make decision tree to find best strategy
        Node decisionTree = new Node(currentWord, otherWords, 0);

        return decisionTree.findSolution();
    }

}
