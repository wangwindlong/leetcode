package leetcode;

//升序数组 旋转后 找出最小值[0,1,2,4,5,6,7] -> [4,5,6,7,0,1,2]
public class m_findmin_153 {
    public static int findMin(int[] datas) {
        return findMin(datas, 0, datas.length - 1);
    }
    //二分查找 递归 注意mid 和 end 边界条件
    //注意有两种情况需要折返到左半边递归查找，就是当前start到end中间有旋转点，需要额外判断：起始点 > 中间点 或者 尾结点 > 起始节点
//    if (datas[start] > datas[mid] || datas[end] > datas[start]) return findMin(datas, start, mid);
    //如果从尾结点判断，条件就很简单
    public static int findMin(int[] datas, int start, int end) {
        if (start >= end) return datas[start];
        int mid = (start + end) / 2;
        if (datas[end] > datas[mid]) return findMin(datas, start, mid);
        else return findMin(datas, mid + 1, end);
    }

    //二分查找 迭代
    public static int findMinBinary(int[] datas) {
        int start = 0, end = datas.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (datas[end] > datas[mid]) end = mid;
            else start = mid + 1;
        }
        return datas[start];
    }


    public static void main(String[] args) {
        int[] datas = new int[]{3,1,2};
        System.out.println(m_findmin_153.findMin(datas));
        System.out.println(m_findmin_153.findMinBinary(datas));
    }
    
}
