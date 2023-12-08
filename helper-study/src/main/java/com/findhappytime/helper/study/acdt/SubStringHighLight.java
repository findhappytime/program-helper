package com.findhappytime.helper.study.acdt;

import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * @author : zhangshu09
 * @date : Created in 2023/12/5 17:45
 * @description :
 * 需求背景：
 * 标记出一句话中所有关键词
 * inpu:我想买苹果手机，请问哪里可以买苹果手机
 * keyword:"苹果", "苹果手机", "哪里"
 * result:我想买[[苹果手机]]，请问[[哪里]]可以买[[苹果手机]]
 * 10w条耗时：41ms
 *
 * 难点：需要考虑单词重叠问题（overlap），例如“苹果手机”同时包含两个关键词，只标记一次。
 *
 * 思路
 *  1. 通过ac自动机遍历得到所有关键词；
 *  2. 新建一个byte[]，长度等于原句子，根据ac算法结果将关键字位置设为1；
 *  3. 将原句子转为char[]，遍历char[]和byte[]，如果byte[]前后位置不一致，则插入替换符；
 *      3.1 如果前一个为1，后一个为0，则插入“]]”；
 *      3.2 如果前一个为0，后一个为1，则插入“[[”；
 *  4. 判断末尾是否插入替换符；
 */
public class SubStringHighLight {


    /**
     * 构建ac自动机
     */
    public static AhoCorasickDoubleArrayTrie<String> buildAcdt(List<String> keywords){
        AhoCorasickDoubleArrayTrie<String> acdt = new AhoCorasickDoubleArrayTrie<>();
        TreeMap<String, String> map = new TreeMap<>();
        for(String keyword : keywords){
            map.put(keyword, keyword);
        }
        acdt.build(map);
        return acdt;
    }

    public static String highLight(String originText, AhoCorasickDoubleArrayTrie<String> acdt){
        List<int[]> hitLocationList = new ArrayList<>();
        // ac算法匹配关键词
        acdt.parseText(originText, (begin, end, value)->{
            int[] indexPair = new int[2];
            indexPair[0] = begin;
            indexPair[1] = end-1;
            hitLocationList.add(indexPair);
        });
        // 构建bitmap
        byte[] posStatus = new byte[originText.length()];
        for(int[] item : hitLocationList){
            posStatus[item[0]] = 1;
            for(int i=item[0]; i<=item[1]; i++){
                posStatus[i] = 1;
            }
        }
        // 字符串拼接
        int lastStatus = 0;
        char[] charArray = originText.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<posStatus.length; i++){
            if(posStatus[i] == lastStatus){
                stringBuilder.append(charArray[i]);
            }else if(0 == lastStatus){
                stringBuilder.append("[[").append(charArray[i]);
                lastStatus = 1;
            }else if(1 == lastStatus){
                stringBuilder.append("]]").append(charArray[i]);
                lastStatus = 0;
            }
            if(i == posStatus.length-1 && 1 == lastStatus){
                stringBuilder.append("]]");
            }
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {


//        String text = "我想买苹果手机，请问哪里可以买苹果";
//        List<String> keywords = Arrays.asList("苹果", "手机", "哪里");

        String text = "你好大家好";
        List<String> keywords = Arrays.asList("好大", "大家");

        AhoCorasickDoubleArrayTrie<String> acdt = buildAcdt(keywords);
        String result = highLight(text, acdt);
        System.out.println("inpu:" + text);
        System.out.println("result:" + result);

        long start = System.currentTimeMillis();
        for(int i=0; i<1000; i++){
            result = highLight(text, acdt);
        }
        long end = System.currentTimeMillis();
        long total = end - start;
        System.out.println("耗时:" + total + "ms");

    }
}
