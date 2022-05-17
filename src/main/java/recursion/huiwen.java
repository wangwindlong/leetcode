package recursion;

//判断字符串是否是回文 C4.17
public class huiwen {
    public static boolean ishuiwen(String str) {
        return ishuiwen(str, 0);
    }
    public static boolean ishuiwen(String str, int n) {
        if (n == str.length() / 2) return true;
        if (str.charAt(n) != str.charAt(str.length() - n - 1)) return false;
        return ishuiwen(str, n+1);
    }

    public static void main(String[] args) {
        System.out.println(huiwen.ishuiwen("abcscba"));

    }

}
