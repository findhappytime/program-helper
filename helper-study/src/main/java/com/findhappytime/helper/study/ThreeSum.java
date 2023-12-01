package com.findhappytime.helper.study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : zhangshu09
 * @date : Created in 2023/11/7 16:01
 * @description : 三数之和
 *
 * 个人理解，三个指针
 *  0. 数组先排好序
 *  1. for 循环 先固定左侧，中间指针加+1  或者sum -1
 *  2. 判断三者和 为0
 *  3. 最后在 左侧指针往后移
 */
public class ThreeSum {


    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i=0; i<nums.length; i++){
            int left = i;
            int right = i+1;
            int sum  = nums.length-1;
            while (right<sum){
                int target = nums[left]+nums[right]+nums[sum];
                if(target ==0){
                    List<Integer> ls = new ArrayList<Integer>();
                    ls.add(nums[left]);
                    ls.add(nums[right]);
                    ls.add(nums[sum]);
                    if(!results.contains(ls)){
                       results.add(ls) ;
                    }
                    right ++;
                    sum --;
                }else if (target < 0){
                    right ++;
                }else {
                    sum --;
                }
            }
        }
        return results;
    }

    public static void main(String[] args){

        List<List<Integer>> lists = threeSum(new int[]{-3, -2, -1, 1, 2, 3, 4, 6, 8, 9});
        for (List<Integer> l :lists) {
            System.out.println(l.toString());
        }

    }
}
