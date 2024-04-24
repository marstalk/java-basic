package fst;

import java.util.HashMap;
import java.util.Map;

public class FST {
    private Map<String, String> transitions;
    private String startState;

    public FST(String startState) {
        transitions = new HashMap<>();
        this.startState = startState;
    }

    public void addTransition(String input, String output) {
        transitions.put(input, output);
    }

    public String apply(String input) {
        String state = startState;
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            String transition = state + input.charAt(i);

            if (transitions.containsKey(transition)) {
                output.append(transitions.get(transition));
                state = transitions.get(transition).substring(transitions.get(transition).length() - 1);
            } else {
                output.append(input.charAt(i));
                state = startState;
            }
        }

        return output.toString();
    }

    public static void main(String[] args) {
        FST fst = new FST("q0");
        fst.addTransition("q0a", "b");
        fst.addTransition("q0b", "c");
        fst.addTransition("q0c", "d");

        String input = "aaabbbccc";
        String output = fst.apply(input);

        System.out.println(output);
    }
}