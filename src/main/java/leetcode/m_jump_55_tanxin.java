package leetcode;

//给定数组是否能从当前下标跳到最后一个位置，贪心算法O(N)复杂度
public class m_jump_55_tanxin {
    //从后往前遍历，只要能跳到前一个位置，那就能跳到最后
    public static boolean canJump(int[] nums) {
        int lastPosition = nums.length;
        for (int i = lastPosition - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPosition) {
                lastPosition = i;
            }
        }
        return lastPosition == 0;
    }

    //从前往后遍历，获取最远距离，判断最远距离大于数组长度即可, 注意循环条件是最后位置的前一个
    public static boolean canJump1(int[] nums) {
        int farPosition = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farPosition = Math.max(i + nums[i], farPosition);
        }
        return farPosition >= nums.length - 1;
    }

    public static void main(String[] args) {
        System.out.println(m_jump_55_tanxin.canJump1(new int[]{4,3,2,1,0,5}));
    }
}
