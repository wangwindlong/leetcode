package leetcode;

public class s_maxDeep_104_tree {

    public static class Node {
        public Integer val;
        Node left;
        Node right;

        public Node(Integer val) {
            this.val = val;
        }
    }

    public int maxDepth(Node root, int deepth) {
        if (root == null) return deepth;
        int left = maxDepth(root.left, deepth + 1);
        int right = maxDepth(root.right, deepth + 1);
        return Math.max(left, right);
    }

    public static void main(String[] args) {
        s_maxDeep_104_tree tree = new s_maxDeep_104_tree();
        Node node = new Node(3);
        node.left = new Node(9);
        node.right = new Node(20);
        node.right.left = new Node(15);
        node.right.right = new Node(9);
        System.out.println(tree.maxDepth(node, 0));
    }

}
