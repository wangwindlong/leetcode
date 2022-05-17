package leetcode;

public class m_validBST_98 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    //解体思路：左子树的上下界为lower 和 root的值，右子树的上下界为root的值和upper
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long lower, long upper) {
        if (root == null) return true;
        if (root.val <= lower || root.val >= upper) return false;
        return isValidBST(root.left, lower, root.val) && isValidBST(root.right, root.val, upper);
    }

    public static void main(String[] args) {
        m_validBST_98 tree = new m_validBST_98();
        m_validBST_98.TreeNode node = new m_validBST_98.TreeNode(14);
        node.left = new m_validBST_98.TreeNode(9);
        node.right = new m_validBST_98.TreeNode(17);
        node.right.left = new m_validBST_98.TreeNode(15);
        node.right.right = new m_validBST_98.TreeNode(22);
        System.out.println(tree.isValidBST(node));
    }
}
