package leetcode;

//leetcode 208
//https://leetcode.cn/problems/implement-trie-prefix-tree/solution/shi-xian-trie-qian-zhui-shu-by-leetcode-ti500/
public class BaseTrie {
    private BaseTrie[] children;
    private boolean isEnd;

    public BaseTrie() {
        children = new BaseTrie[26];
        isEnd = false;
    }

    public void insert(String word) {
        BaseTrie trie = this;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (trie.children[index] == null) trie.children[index] = new BaseTrie();
            trie = trie.children[index];
        }
        trie.isEnd = true;
    }

    public boolean search(String word) {
        BaseTrie trie = searchPrefix(word);
        return trie != null && trie.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private BaseTrie searchPrefix(String prefix) {
        BaseTrie trie = this;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            trie = trie.children[index];
            if (trie == null) return null;
        }
        return trie;
    }

}
