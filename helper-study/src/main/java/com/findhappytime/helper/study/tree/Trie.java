package com.findhappytime.helper.study.tree;

/**
 * @author : zhangshu09
 * @date : Created in 2023/12/6 10:52
 * @description :
 */
public class Trie {
    // 存储子
    private Trie[] children;
    private boolean isEnd;

    public Trie(){
        children = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) {
        Trie curNode = this;
        for(int i = 0; i<word.length(); i++){
            char c = word.charAt(i);
            // 确定子节点位置
            int index = c-'a';
            if(curNode.children[index] == null){
                curNode.children[index] = new Trie();
            }
            // 设置当前节点 为该孩子节点
            curNode = curNode.children[index];
        }
        curNode.isEnd =true;
    }

    /**
     * 完全匹配
     * @param word
     * @return
     */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node !=null && node.isEnd;

    }

    // 前缀匹配
    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node !=null;
    }

    private Trie searchPrefix(String prefix) {
        Trie curNode = this;
        for(int i=0; i<prefix.length();i++){
            char c = prefix.charAt(i);
            int index = c-'a';
            if(curNode.children[index] ==null){
                // 代表前缀没找到
                return null;
            }
            curNode.children[index] = new Trie();
        }
        return curNode;
    }
}
