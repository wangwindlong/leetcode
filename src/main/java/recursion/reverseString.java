package recursion;

import java.util.ArrayList;
import java.util.List;

//字符串逆序 C4.16
public class reverseString {

    public static String reverse(String str) {
        return String.copyValueOf(reverse(str.toCharArray(), 0));
    }

    //递归
    public static char[] reverse(char[] str, int cur) {
        if (cur >= str.length / 2) {
            return str;
        }
        char tmp = str[str.length - cur - 1];
        str[str.length - cur - 1] = str[cur];
        str[cur] = tmp;
        return reverse(str, cur + 1);
    }

    public static char[] reverse(char[] str, int start, int stop) {
        if (start >= stop) return str;
        char tmp = str[stop];
        str[stop] = str[start];
        str[start] = tmp;
        return reverse(str, start + 1, stop - 1);
    }

    public static void main(String[] args) {
        String str = "nihaoma@abcd";
        System.out.println(reverseString.reverse(str));
        System.out.println(reverseString.reverse(str.toCharArray(), 0, str.length() - 1));
    }

}
