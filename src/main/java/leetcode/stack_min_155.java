package leetcode;

import java.util.Stack;

public class stack_min_155 {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();
    public stack_min_155() {
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.push(val);
        minStack.push(Math.min(minStack.peek(), val));
    }

    public int pop() {
        minStack.pop();
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        stack_min_155 stack = new stack_min_155();

        stack.push(-1);
        stack.push(1);
        stack.push(-91);
        stack.push(100);
        stack.push(-2);
        System.out.println(stack.getMin());
        stack.pop();
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
//        System.out.println(stack.pop());
        System.out.println(stack.getMin());
    }
}
