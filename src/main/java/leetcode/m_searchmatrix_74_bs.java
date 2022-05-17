package leetcode;

//二维矩阵搜索 二分查找，注意while条件 right初始化和-1
public class m_searchmatrix_74_bs {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int col = matrix[0].length;
        int left = 0, right = matrix.length * col - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int x = matrix[mid/col][mid%col];
            if (x < target) {
                left = mid + 1;
            } else if (x > target) {
                right = mid - 1;
            } else return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[4][4];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = i * 4 + j + 1;
            }
        }
        System.out.println(m_searchmatrix_74_bs.searchMatrix(matrix, 2));
    }
}
