package recursion;

//是否是元音字母多余辅音字母 C4.18
public class fuyin {
    public static boolean isyuanyin(String str) {
        return isyuanyin(str, 0, 0, 0);
    }

    public static boolean isyuanyin(String str, int cur, int y, int f) {
        if (cur >= str.length()) return y > f;
        char c = str.charAt(cur);
        boolean isY = c == 'a' ||c == 'e' ||c == 'i' ||c == 'o' ||c == 'u';
        return isyuanyin(str, cur + 1, y + (isY ? 1: 0), f + (isY ? 0 : 1));
    }

    public static void main(String[] args) {
        System.out.println(fuyin.isyuanyin("afoseidfouj"));
    }

}
