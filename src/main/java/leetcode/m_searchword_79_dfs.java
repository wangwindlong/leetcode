package leetcode;

//单词搜索，给定一个二维数组，找到这个单词
public class m_searchword_79_dfs {

    int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    public boolean searchword(char[][] datas, String word) {
        if (word.length() == 0) return true;
        boolean[][] visited = new boolean[datas.length][datas[0].length];
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[0].length; j++) {
                boolean exist = searchword(datas, word, i, j, 0, visited);
                if (exist) return true;
            }
        }
        return false;
    }

    public boolean searchword(char[][] datas, String word, int i, int j, int cur, boolean[][] visited) {
        if (cur == word.length() - 1) return datas[i][j] == word.charAt(cur); //这个条件为什么必须加 不能直接return true？
        char c = datas[i][j];
        if (c != word.charAt(cur)) return false;
        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int a = i + dx[k], b = j + dy[k];
            if (a < 0 || a > visited.length - 1 || b < 0 || b > visited[0].length - 1 || visited[a][b]) continue;
            if (searchword(datas, word, a, b, cur + 1, visited)) return true;
        }
        visited[i][j] = false;
        return false;
    }

    public boolean isMatch() {
        return true;
    }

    public static void main(String[] args) {
        char[][] datas = new char[][]{{'a', 'b', 'c', 'e'},
                {'s', 'f', 'c', 's'},
                {'a', 'd', 'e', 'f'},
                {'a', 'd', 'e', 'e'}};
        char[][] data2 = new char[1][1];
        data2[0][0] = 'a';
        System.out.println(new m_searchword_79_dfs().searchword(datas, "abcced"));
        System.out.println(new m_searchword_79_dfs().searchword(data2, "a"));

    }

}
