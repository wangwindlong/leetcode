package leetcode;

public class m_search_33 {

    //找到升序数组 某个点旋转后的位置 [4,5,6,0,1,2] -> 3
    public static int test(int[] nums) {
        if (nums.length <= 1) return -1;
        if (nums.length == 2) return 1;
        int start = nums[0];
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid - 1]) {
                return mid;
            } else if (nums[mid] < start) { //比第一个数小，向左找
                right = mid -1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    //通过判断是否在mid右边的情况来二分查找 注意边界条件，以免死循环或漏情况
    public static int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int start = nums[0];
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (start <= nums[mid] && (target > nums[mid] || target < start)) {
                left = mid + 1; //如果左边单调且target大于mid或小于0值
            } else if (start > nums[mid] && target > nums[mid] && target < start) {
                left = mid + 1; //如果左边不单调且target大于mid且大于0值
            } else {
                right = mid;
            }
        }
        return nums[left] == target ? left : -1;
    }

    public static void main(String[] args) {
        System.out.println(m_search_33.test(new int[]{4,5,2}));
        System.out.println(m_search_33.search(new int[]{5, 1, 3}, 3));
    }
}
