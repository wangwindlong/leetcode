package binary;

public class binarysum {
    public static int binary_sum(int[] data) {
        return binary_sum(data, 0 , data.length - 1);
//        return binary_sum2(data, 0 , data.length);
    }

    //右闭区间，调用时要length - 1，递归时要 mid - 1
    private static int binary_sum(int[] data, int l, int r) {
        System.out.println("binary_sum called l=" + l + ",r =" + r); // 15次
        if (l >= r) {
            return data[l];
        } else if (l == r - 1) {
            return data[l] + data[r];
        }else {
            int mid = (l + r) / 2;
            return binary_sum(data, l, mid - 1) + binary_sum(data, mid, r);
        }
    }
    //右开区间，调用时直接length
    private static int binary_sum2(int[] data, int l, int r) {
        System.out.println("binary_sum2 called l=" + l + ",r =" + r); //23次
        if (l >= r) {
            return 0;
        } else if (l == r - 1) {
            return data[l];
        }else {
            int mid = (l + r) / 2;
            return binary_sum2(data, l, mid) + binary_sum2(data, mid, r);
        }
    }

    public static void main(String[] args) {
        int[] data = new int[] {1,2,3,4,5,6,7,8,9,10,11,12};
        System.out.println(binarysum.binary_sum(data));
    }
}
