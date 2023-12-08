package com.findhappytime.helper.study.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author : zhangshu09
 * @date : Created in 2023/12/1 15:31
 * @description : 回溯
 */
public class FullPermutations2 {

    public static void main(String[] args){
        String[] src = new String[]{"a","b","c"};

        List<List<String>> results = new ArrayList<List<String>>();
        int length = src.length;
        boolean[] isUsed = new boolean[length];
        Stack<String> path = new Stack<String>();

        traverseTree(src, length, 0, isUsed, path, results);

        for (List<String> p : results) {
            System.out.println(p.toString());
        }
    }
    public static void traverseTree(String[] src, int len, int dept, boolean[] isUsed,Stack<String> path, List<List<String>> results){
        if(dept==len){
            results.add(new ArrayList<String>(path));
            return;
        }
        for(int i=0; i< len; i++){
            if(!isUsed[i]){
                path.push(src[i]);
                isUsed[i] =true;
                traverseTree(src,len,dept+1,isUsed,path,results);
                isUsed[i] = false;
                path.pop();
            }
        }

    }

}
