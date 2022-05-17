package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Stack_kuohao_20 {
    private Map<Integer, Integer> map = new HashMap<>();
    public Stack_kuohao_20() {
        map.put((int) '{', (int) '}');
        map.put((int) '[', (int) ']');
        map.put((int) '(', (int) ')');
        map.put((int) '?', (int) '?');
    }
    private boolean isLegal(String s) {
        int length = s.length();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < length; i++) {
            int c = s.charAt(i);
            if (map.containsKey(c)) {
                stack.push(c);
            } else if (map.get(stack.peek()) == c) {
                stack.pop();
            } else return false;
        }
        return stack.empty();
    }

    private boolean isLegal2(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '(') {
                stack.push(')');
            } else if (stack.empty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        Stack_kuohao_20 kuohao = new Stack_kuohao_20();
        System.out.println(kuohao.isLegal2("()[]{}[{[()]}]"));
    }
}
