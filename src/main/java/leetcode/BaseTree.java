package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseTree {
    public static class TreeNode<T> {
        T val;
        TreeNode<T> left;
        TreeNode<T> right;
        TreeNode(T val) {
            this.val = val;
        }
    }

    // type 0 前序遍历 1中序遍历 2后续遍历
    public <T> void  printTree(TreeNode<T> root, int type) {
        if (root == null) {
            System.out.println("treenode is null");
            return;
        }
        List<T> res = new ArrayList<T>();
        if (type == 0) preOrder(root, res);
        else if (type == 1) inOrder(root, res);
        else if (type == 2) postOrder(root, res);
        System.out.println(Arrays.toString(res.toArray()));
    }

    private <T> void preOrder(TreeNode<T> root, List<T> res) {
        if (root == null) return;
        res.add(root.val);
        System.out.println(root.val);
        preOrder(root.left, res);
        preOrder(root.right, res);
    }

    private <T> void inOrder(TreeNode<T> root, List<T> res) {
        if (root == null) return;
        preOrder(root.left, res);
        res.add(root.val);
        preOrder(root.right, res);
    }

    private <T> void postOrder(TreeNode<T> root, List<T> res) {
        if (root == null) return;
        preOrder(root.left, res);
        preOrder(root.right, res);
        res.add(root.val);
    }

}
