package leetcode;

import java.util.Arrays;

//贪心算法，s个饼干满足g个孩子的胃口
public class s_cookie_455_tanxin {
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int glength = g.length;
        int clength = s.length;
        int count = 0;
        for (int i = 0, j = 0; i < glength && j < s.length; i++, j++) {
            while (j < clength && g[i] > s[j]) { //如果s饼干一直不满足当前小孩的胃口，遍历直到结束
                j++;
            }
            if (j < clength) count++; //如果饼干没有遍历结束，则说明找到了满足小孩胃口的饼干，加一
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(s_cookie_455_tanxin.findContentChildren(new int[]{1,2}, new int[]{1, 3, 2}));
    }
}
