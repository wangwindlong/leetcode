package leetcode;

//柠檬水找零 5、10、20美元，确认是否能够正常卖出所有的柠檬水
public class s_lemonade_860_tanxin {
    public static boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) five++;
            else if (bill == 10) {ten++; five--;}
            else if (ten > 0) {ten--; five--;} //如果是20面值，则优先减10块面值
            else five -= 3;//如果是20面值且没有10块面值，则减3个5块面值
            if (five < 0) return false;//如果没有5块面值则不能卖
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(s_lemonade_860_tanxin.lemonadeChange(new int[] {5,5,10,10,20}));
    }
}
