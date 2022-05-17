package leetcode;

import java.util.Stack;

//232题 使用栈来实现队列 先进先出
public class stack_queue_232 {
    Stack<Integer> in = new Stack<>();
    Stack<Integer> out = new Stack<>();

    public void push(int x) {
        in.push(x);
    }

    public int pop() {
        if (out.empty()) {
            in2Out();
        }
        return out.pop();
    }

    public int peek() {
        if (out.empty()) {
            in2Out();
        }
        return out.peek();
    }

    public boolean empty() {
        return out.isEmpty() && in.empty();
    }

    private void in2Out() {
        while (!in.empty()) {
            out.push(in.pop());
        }
    }

    public static void main(String[] args) {
        stack_queue_232 stack = new stack_queue_232();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop()); //1  [2,3]
        System.out.println(stack.pop()); //2  [3]
        stack.push(4); //[3,4]
        System.out.println(stack.pop()); //3 [4]
        System.out.println(stack.pop()); //4 []
        stack.push(5); //[5]
        stack.push(6); //[5,6]

        System.out.println(stack.peek()); //5
        System.out.println(stack.pop()); //5 [6]
    }
}
