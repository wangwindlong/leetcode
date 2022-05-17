package recursion;

import java.util.Arrays;

//排序 使得所有偶数在所有奇数前面 C4.19
public class sortodd {
    public static int[] sortOdd(int[] datas) {
        return sortOdd(datas, 0, 1);
    }
    //双指针odd指向最开始的奇数，cur则遍历所有元素
    public static int[] sortOdd(int[] datas, int odd, int cur) {
        if (cur >= datas.length) return datas;
        int nextodd = (datas[odd] & 1) == 0 ? odd + 1 : odd; //如果当前指向的是偶数，那么odd+1
        if ((datas[cur] & 1) == 0 && cur >= nextodd) {//如果当前cur指向的是偶数，且大于odd指向的位置
            int tmp = datas[cur];//替换
            datas[cur] = datas[odd];
            datas[odd] = tmp;
        }
        cur++;//向后遍历
        return sortOdd(datas, nextodd, cur);
    }
    public static void main(String[] args) {
        int[] datas = new int[]{1,2,3,4,5,6,7,8};
        System.out.println(Arrays.toString(sortodd.sortOdd(datas)));

    }

}
