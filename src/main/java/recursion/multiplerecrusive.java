package recursion;

import java.util.ArrayList;
import java.util.List;

public class multiplerecrusive {

    public static String list2String(List<Character> s) {
        StringBuilder sb = new StringBuilder("");
        for (char c: s){
            sb.append(c);
        }
        return sb.toString();
    }
    public static List<String> kuohao(char[] datas, int n, List<Character> s, List<String> res) {
        if (s.size() >= n) res.add(list2String(s));
        else for (char c : datas){
            s.add(c);
            kuohao(datas, n, s, res);
            //如果是string则不需要 因为每次都是新建 不会继承上一次递归的副作用
            s.remove(s.size() - 1); //list 需要清除上一次的状态
        }
        return res;
    }
    public static List<String> kuohao(char[] datas, int n, String s, List<String> res) {
        if (s.length() >= n) res.add(s);
        else for (char c : datas) kuohao(datas, n, s + c, res);
        return res;
    }
    public static void main(String[] args) {
        char[] datas = new char[]{'(', ')'};
        System.out.println(multiplerecrusive.kuohao(datas, 3, "", new ArrayList<>()));
        System.out.println(multiplerecrusive.kuohao(datas, 3, new ArrayList<>(), new ArrayList<>()));

    }

}
