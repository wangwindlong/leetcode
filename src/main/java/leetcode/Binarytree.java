package leetcode;


import java.util.*;

// 四种遍历方式：前序 中序 后序 层序
// https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/tu-jie-er-cha-shu-de-si-chong-bian-li-by-z1m/
public class Binarytree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //递归实现中序遍历
    public void inOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inOrder(root.left, res);
        res.add(root.val);
        inOrder(root.right, res);
    }

    //递归实现后序遍历
    public void postOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inOrder(root.left, res);
        inOrder(root.right, res);
        res.add(root.val);
    }

    //迭代 中序遍历 [4, 1, 3, 2, 5]
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
//        inOrder(root, res);//递归实现
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }


    //前序迭代
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;

        }
        return res;
    }

    //后序迭代 43521 https://leetcode-cn.com/problems/binary-tree-postorder-traversal/solution/er-cha-shu-de-hou-xu-bian-li-by-leetcode-solution/
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.peek();
            //如果没有右子树，或者已经遍历过右子树，此时为右子节点
            if (root.right == null || prev == root.right) {
                res.add(root.val);
                prev = root;
                root = null;
                stack.pop();
            } else {
                root = root.right;
            }
        }
        return res;
    }

    //DFS层序遍历 102 二叉树的层序遍历
    public List<List<Integer>> levelOrderDFS(TreeNode root) {
        List<List<Integer>> allResults = new ArrayList<>();
        if (root == null) {
            return allResults;
        }
        travel(root, 0, allResults);
        return allResults;
    }

    private void travel(TreeNode root, int level, List<List<Integer>> results) {
        if (results.size() == level) {
            results.add(new ArrayList<>());
        }
        results.get(level).add(root.val);
        if (root.left != null) {
            travel(root.left, level + 1, results);
        }
        if (root.right != null) {
            travel(root.right, level + 1, results);
        }
    }
    //层序遍历 广度优先bfs 按层序保存数组
    //https://leetcode.cn/problems/binary-tree-level-order-traversal/solution/er-cha-shu-de-ceng-xu-bian-li-by-leetcode-solution/
    public List<List<Integer>> levelOrderBFS(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (root == null) return ret;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int currentLevelSize = queue.size();
            // 变量 i 无实际意义，只是为了循环 n 次
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }
        return ret;
    }

    //morris 中序迭代
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        return res;
    }

    public static void main(String[] args) {
        Binarytree tree = new Binarytree();
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(4);
        node.right = new TreeNode(2);
        node.right.left = new TreeNode(3);
        node.right.right = new TreeNode(5);
        node.right.left.left = new TreeNode(6);
        node.right.left.right = new TreeNode(7);
        node.right.right.left = new TreeNode(8);
        node.right.right.right = new TreeNode(9);
        System.out.println("中序遍历");
        List<Integer> res = tree.inorderTraversal(node);
        System.out.println(Arrays.toString(res.toArray()));
        System.out.println("后序遍历");
        List<Integer> post = new ArrayList<>();
        tree.postOrder(node, post);
        System.out.println(Arrays.toString(post.toArray()));
        System.out.println(Arrays.toString(tree.postorderTraversal(node).toArray()));
        System.out.println("层序遍历深度优先");
        System.out.println(Arrays.toString(tree.levelOrderDFS(node).toArray()));
        System.out.println("层序遍历广度优先");
        System.out.println(Arrays.toString(tree.levelOrderBFS(node).toArray()));

    }


}
