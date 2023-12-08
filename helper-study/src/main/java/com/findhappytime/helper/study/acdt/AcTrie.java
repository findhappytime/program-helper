package com.findhappytime.helper.study.acdt;


import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author : zhangshu09
 * @date : Created in 2023/12/6 11:19
 * @description : AC自动机(Aho-Corasick automaton)  多模式字符串匹配算法
 */
public class AcTrie {

    public static void main(String[] args) {

        // 现成的算法有如下:
        // AhoCorasickDoubleArrayTrie<String> acdt = buildAcdt(keywords);

        String text = "大大,你好大家好,真大";
        List<String> keywords = Arrays.asList("好大", "大家", "大");

        String result = AcTrie.matchKeys(text, keywords, Pair.of("<highlight>", "</highlight>"));
        System.out.println("input:" + text);
        System.out.println("result:" + result);
    }

    private static String matchKeys(String text, List<String> keywords, Pair<String, String> highlightMark) {
        AcTrie acdt = AcTrie.buildAcTrie(keywords);
        String result = highlight(text, acdt, highlightMark);
        return result;
    }


    //根节点
    private AcNode root;

    public AcTrie() {
        this.root = new AcNode();
    }

    /**
     * 插入模式串，构建Trie
     */
    public void insert(String word) {
        AcNode currNode = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!currNode.next.containsKey(c)) {
                currNode.next.put(c, new AcNode());
            }
            currNode = currNode.next.get(c);
        }
        currNode.depth = word.length();
    }

    /**
     * 为所有节点，构建失配指针 一个bfs(广度优先搜索)层序遍历
     */
    public void buildFailurePointer() {
        Queue<AcNode> queue = new LinkedBlockingDeque<>();

        //将所有root 的直接子节点的 失配指针 指向root，并加入队列queue
        for (AcNode acNode : root.next.values()) {
            acNode.failure = root;
            queue.add(acNode);
        }
        //广度优先 构建失配指针
        while (!queue.isEmpty()) {
            AcNode pNode = queue.poll();
            for (Map.Entry<Character, AcNode> characterAcNodeEntry : pNode.next.entrySet()) {
                // 获取父节点的失配指针
                AcNode pFailure = pNode.failure;
                // 获取字节点的 节点 和 字符
                Character childKey = characterAcNodeEntry.getKey();
                AcNode childNode = characterAcNodeEntry.getValue();

                // 如果父节点不为null, 且其字节点对应的字符中，没有包含当前节点的字符
                while (pFailure != null && !pFailure.hasNext(childKey)) {
                    //继续向上 重复判断。
                    pFailure = pFailure.failure;
                }
                // 表明找到了跟节点，且root 的子节点没有匹配
                if (pFailure == null) {
                    childNode.failure = root;
                } else { // 父节点的 子节点 含有对应的字符
                    childNode.failure = pFailure.getNext(childKey);
                }
                queue.add(childNode);

            }
        }
    }

    /**
     * 匹配文本
     *
     * @param text
     * @return List<ParseResult>
     */
    public List<ParseResult> parseText(String text) {
        List<ParseResult> parseResults = new ArrayList<>();
        char[] chars = text.toCharArray();
        //从根节点开始匹配
        AcNode cur = root;
        //遍历字符串的每个字符
        for (int i = 0; i < chars.length; i++) {
            //当前字符
            char nextKey = chars[i];
            //如果cur不为null，并且当前节点的的子节点不包括当前字符，即不匹配
            while (cur != null && !cur.hasNext(nextKey)) {
                //那么通过失配指针转移到下一个节点继续匹配
                cur = cur.failure;
            }
            //如果节点为null，说明当前字符匹配到了根节点且失败
            //那么继续从根节点开始进行下一轮匹配
            if (cur == null) {
                cur = root;
            } else {
                //匹配成功的节点
                cur = cur.getNext(nextKey);
                //继续判断
                AcNode temp = cur;
                while (temp != null) {
                    //如果当前节点是某个关键词的结尾，那么取出来
                    if (temp.depth != 0) {
                        int start = i - temp.depth + 1, end = i;
                        parseResults.add(new ParseResult(start, end, new String(chars, start, temp.depth)));
                    }
                    // 继续判断该节点的失配指针节点
                    // 因为失配指针节点表示的模式串是当前匹配的模式串的在这些关键词中的最长后缀
                    // 且由于当前节点的路径包括了失配指针的全部路径
                    // 并且失配指针路径也是一个完整的关键词，需要找出来。
                    temp = temp.failure;
                }
            }
        }
        return parseResults;
    }

    /**
     * 使用高亮标识，高亮关键词
     *
     * @param originText
     * @param acTrie
     * @param highlightMark
     * @return String
     */
    public static String highlight(String originText, AcTrie acTrie, Pair<String, String> highlightMark) {
        List<ParseResult> parseResults = acTrie.parseText(originText);

        // 构建bitmap --标记匹配到关键词的位置
        byte[] posStatus = new byte[originText.length()];
        for (ParseResult pr : parseResults) {
            posStatus[pr.startIndex] = 1;
            for (int i = pr.startIndex; i <= pr.endIndex; i++) {
                posStatus[i] = 1;
            }
        }
        // 字符串拼接
        // 判断当前状态用
        int lastStatus = 0;
        char[] charArray = originText.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < posStatus.length; i++) {
            // 当前状态没变, 不增加高亮标识
            if (posStatus[i] == lastStatus) {
                stringBuilder.append(charArray[i]);
            } else if (0 == lastStatus) {
                stringBuilder.append(highlightMark.getKey()).append(charArray[i]);
                lastStatus = 1;
            } else if (1 == lastStatus) {
                stringBuilder.append(highlightMark.getValue()).append(charArray[i]);
                lastStatus = 0;
            }
            // 最后一个位置判断
            if (i == posStatus.length - 1 && 1 == lastStatus) {
                stringBuilder.append(highlightMark.getValue());
            }
        }

        return stringBuilder.toString();
    }

    /**
     * 构建ac自动机
     */
    public static AcTrie buildAcTrie(List<String> keywords) {
        AcTrie acTrie = new AcTrie();
        for (String keyword : keywords) {
            acTrie.insert(keyword);
        }
        acTrie.buildFailurePointer();
        return acTrie;
    }


    class AcNode {

        // 子节点
        Map<Character, AcNode> next = new HashMap<>();

        // 模式串深度
        int depth;

        // 失配指针
        AcNode failure;

        public boolean hasNext(char nextKey) {
            return next.containsKey(nextKey);
        }

        public AcNode getNext(char nextKey) {
            return next.get(nextKey);
        }


    }

    /**
     * 匹配结果对象
     */
    class ParseResult {
        int startIndex;
        int endIndex;
        String key;

        public ParseResult(int startIndex, int endIndex, String key) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.key = key;
        }

        @Override
        public String toString() {
            return "{" +
                    "startIndex=" + startIndex +
                    ", endIndex=" + endIndex +
                    ", key='" + key + '\'' +
                    '}';
        }
    }

}
