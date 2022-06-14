package recursion;

import java.util.*;

public class PuzzleSolve {
    public static boolean check( Map<String, Integer> map) {
        String res = "";
        for (String key : map.keySet()) {
            res += map.get(key);
        }
        return res.equals("423");
    }
    public static Map<String, Integer> solve(int k, Map<String, Integer> map, Set<Integer> elements) {
        Set<Integer> sets = new HashSet<>(elements);
        for (int num: sets) {
            elements.remove(num);
            map.put(""+num, num);
            if (k == 1) {
                System.out.println(map);
                if (check(map)) System.out.println("find fit result:" + map);
            } else solve(k - 1, map, elements);
            map.remove(""+num);
            elements.add(num);
        }
        return map;
    }
    public static void main(String[] args) {
        Set<Integer> elements = new HashSet<>();
        elements.add(1);
        elements.add(2);
        elements.add(3);
        elements.add(4);
        System.out.println(PuzzleSolve.solve(3, new LinkedHashMap<>(), elements));

    }

}
