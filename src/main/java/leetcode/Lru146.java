package leetcode;

import java.util.HashMap;
import java.util.Map;

class Lru146 {
    static class Node {
        int key;
        int value;
        private Node prev;
        private Node next;
        public Node() {}
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    Map<Integer, Node> cache = new HashMap<>();
    private int size = 0;
    private int capacity = 0;
    private Node head = new Node();
    private Node tail = new Node();

    public Lru146(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node != null) {
            movetoHead(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            //判断是否超过capacity
            if (size >= capacity) {
                Node removed = tail.prev;
                removeNode(removed);
                cache.remove(removed.key);
                size --;
            }
            node = new Node(key, value);
            addToHead(node);
            cache.put(key, node);
            size ++;
        } else {
            node.value = value;
            movetoHead(node);
        }
    }

    private void movetoHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(Node node) {
        head.next.prev = node;
        node.next = head.next;
        node.prev = head;
        head.next = node;
    }
    private void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    public static void main(String[] args) {
        Lru146 cache = new Lru146(10);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.get(2);
        System.out.println(cache.cache);

    }

}

