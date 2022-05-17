package leetcode;

//判断是否是完全平方数
public class s_issquare_367_bs {
    public static boolean isSquare(int value) {
        if (value <= 1) return true;
        int right = value / 2;
        int left = 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid * mid < value) {
                left = mid + 1;
            } else if (mid * mid > value) {
                right = mid - 1;
            } else return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(s_issquare_367_bs.isSquare(49));
    }
}
