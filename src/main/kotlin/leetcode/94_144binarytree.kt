package leetcode



public class TreeNode {
    var value: Int = 0


}

fun main() {
    val tree = Binarytree()

    val node = Binarytree.TreeNode(1)
    node.left = Binarytree.TreeNode(2)
    node.right = Binarytree.TreeNode(6)
    node.left.left = Binarytree.TreeNode(3)
    node.left.right = Binarytree.TreeNode(5)
    node.left.left.right = Binarytree.TreeNode(4)
    node.right.left = Binarytree.TreeNode(7)
    node.right.right = Binarytree.TreeNode(8)
    //中序遍历
    println(tree.inorderTraversal(node).toTypedArray().contentToString())
    //前序遍历
    println(tree.preorderTraversal(node).toTypedArray().contentToString())
    //后序遍历
    println(tree.postorderTraversal(node).toTypedArray().contentToString())
}