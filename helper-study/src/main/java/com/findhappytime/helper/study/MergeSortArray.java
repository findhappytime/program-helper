package com.findhappytime.helper.study;

import java.util.Arrays;

/**
 * @author : zhangshu09
 * @date : Created in 2023/11/7 15:47
 * @description :  合并两个有序数组
 */
public class MergeSortArray {

    public static int[] merge(int[] nums1, int[] nums2) {

        int[] result = new int[nums1.length+nums2.length];
        int p1=0;
        int p2=0;
        int p=0;
        
        while (p1<nums1.length && p2<nums2.length){
            if(nums1[p1] <= nums2[p2]){
                result[p]= nums1[p1];
                p1=p1+1;
            }else {
                result[p] = nums2[p2];
                p2=p2+1;
            }
            p=p+1;
        }
        while (p1<nums1.length){
            result[p]= nums1[p1];
            p1=p1+1;
            p=p+1;
        }

        while (p2<nums2.length){
            result[p] = nums2[p2];
            p2=p2+1;
            p=p+1;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] merge = merge(new int[]{1, 3, 4, 5, 6}, new int[]{3, 4, 7, 8});
        System.out.println(Arrays.toString(merge));

    }
}
