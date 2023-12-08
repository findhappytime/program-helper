package com.findhappytime.helper.study;


import java.util.HashSet;
import java.util.Set;

/**
 * @author : zhangshu09
 * @date : Created in 2023/12/5 16:42
 * @description : 无重复字符的最长子串
 * 待理解
 */
public class LongestSubstring {

    public static void main(String[] args) {

        int result = lengthOfLongestSubstring("sdbdhskxshsbsj");
        System.out.println(result);

    }

    /**
     * leecode 官网解答1
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s){
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();

        int rk = -1, ans = 0;
        for(int i =0; i<n; ++i){
            if(i !=0){
                occ.remove(s.charAt(i-1));
            }
            while (rk+1 < n && !occ.contains(s.charAt(rk+1))){
                occ.add(s.charAt(rk+1));
                ++rk;
            }
            ans = Math.max(ans, rk-i+1);
        }
        return ans;
    }



}
