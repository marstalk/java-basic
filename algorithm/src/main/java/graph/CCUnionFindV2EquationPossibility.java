package graph;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/satisfiability-of-equality-equations/
 */
public class CCUnionFindV2EquationPossibility {

    public static void main(String[] args) {
        CCUnionFindV2EquationPossibility possibility = new CCUnionFindV2EquationPossibility();
        System.out.println(possibility.equationPossible(new String[]{"a==b", "b!=a"})); // false
        System.out.println(possibility.equationPossible(new String[]{"b==a", "a==b"})); // true
        System.out.println(possibility.equationPossible(new String[]{"a==b", "b==c", "a==c"})); // true
        System.out.println(possibility.equationPossible(new String[]{"a==b", "b!=c", "c==a"})); // false

    }

    public boolean equationPossible(String[] equations) {
        // 1. construct the forest.
        UF uf = new UF();

        // 2. add node to forest and union those a==b variables(a,b) into a tree.
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                char a = equation.charAt(0);
                char b = equation.charAt(3);
                uf.add(a);
                uf.add(b);
                uf.union(a, b);
            }
        }

        // 3. add node to forest and check connection for those a!=b variables(a,b), if connected, there is contradiction
        // return false
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                char a = equation.charAt(0);
                char b = equation.charAt(3);
                uf.add(a);
                uf.add(b);
                if (uf.connected(a, b)) {
                    return false;
                }
            }
        }

        // 4.
        return true;
    }
}

class UF {
    private Map<Character, Character> parent;

    public UF() {
        this.parent = new HashMap<>();
    }

    public void add(char a) {
        if (parent.containsKey(a)) return;
        parent.put(a, a);
    }

    public void union(char a, char b) {
        // connected, return;
        if (a == b) return;

        // on the same tree, connected, return
        char rootA = findRoot(a);
        char rootB = findRoot(b);
        if (rootA == rootB) return;

        // make it the same tree.
        parent.put(rootA, rootB);
    }

    public boolean connected(char a, char b) {
        return findRoot(a) == findRoot(b);
    }

    private char findRoot(char a) {
        // if a's parent is not root, then try to set parent as root.
        if (parent.get(a) != a) {
            parent.put(a, findRoot(parent.get(a)));
        }

        // here, a's parent is the root.
        return parent.get(a);
    }
}