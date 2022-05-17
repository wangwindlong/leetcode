package leetcode;

//反转二叉树，给定[4,2,7,1,3,6,9] 返回[4,7,2,9,6,3,1]
public class s_reversetree_226 extends BaseTree {

    public TreeNode<Integer> invertTree(TreeNode<Integer> root) {
        invert(root);
        return root;
    }

    private void invert(TreeNode root) {
        if (root == null) return;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invert(root.left);
        invert(root.right);
    }

    public static void main(String[] args) {
        s_reversetree_226 reverse = new s_reversetree_226();
        BaseTree.TreeNode<Integer> node = new BaseTree.TreeNode<Integer>(3);
        node.left = new BaseTree.TreeNode<Integer>(9);
        node.right = new BaseTree.TreeNode<Integer>(20);
        node.right.left = new BaseTree.TreeNode<Integer>(15);
        node.right.right = new BaseTree.TreeNode<Integer>(9);
        reverse.printTree(reverse.invertTree(node), 0);
    }
    
}
