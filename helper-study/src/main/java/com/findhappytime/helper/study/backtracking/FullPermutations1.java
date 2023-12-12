package com.findhappytime.helper.study.backtracking;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author : zhangshu09
 * @date : Created in 2023/11/7 14:24
 * @description : 全排列  递归与回溯
 * ⭐️ 需要再理解
 */
public class FullPermutations1 {

    public static List<List<Integer>> permute(int[] ins) {
        int length = ins.length;
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        Stack<Integer> path = new Stack<Integer>();
        boolean[] isUsed = new boolean[length];
        dfs(ins, length, 0, path, isUsed, results);
        return results;
    }

    private static void dfs(int[] nums, int len, int depth, Stack<Integer> path, boolean[] isUsed, List<List<Integer>> results) {
        if (depth == len) {
            results.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (isUsed[i]) {
                continue;
            }
            path.push(nums[i]);
            isUsed[i] = true;
            dfs(nums, len, depth + 1, path, isUsed, results);
            isUsed[i] = false;
            path.pop();
        }


    }

    public static void main(String[] args) {
        List<List<Integer>> permute = permute(new int[]{1, 2, 3});
        for (List<Integer> p : permute) {
            System.out.println(p.toString());
        }

    }
}
