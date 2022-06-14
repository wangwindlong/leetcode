package leetcode;

//大写转小写
public class s_lowercase_709 {
    public  String toLowerCase(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                res.append((char) (c-'A' + 'a'));
            } else res.append(c);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new s_lowercase_709().toLowerCase("HelloWorld"));

    }

}
