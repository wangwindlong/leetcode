package recursion;

//确定元素是否都是唯一的 C4-11
public class unique {
    public static boolean unique(int[] datas) {
        return unique(datas, 0, datas.length - 1);
    }
    public static boolean unique(int[] datas, int left, int end) {
        if (left + 1 >= end) return datas[left] != datas[end];
        boolean isunique = unique(datas, left + 1, end);
        if (!isunique) return false;
        for (int i = left + 1; i <= end; i++) {
            if (datas[left] == datas[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(unique.unique(new int[]{4,3,1,3}));
    }
}
