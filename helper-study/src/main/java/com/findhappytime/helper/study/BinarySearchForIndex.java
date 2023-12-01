package com.findhappytime.helper.study;

/**
 * @author : zhangshu09
 * @date : Created in 2023/11/7 16:39
 * @description : 二分查找   数组一定是排好序的数组
 * 个人理解：
 *  0. 数组先排好序
 *  1. 取中间的位置，比较和目标值的大小
 *  2. 这半二分查找r
 */
public class BinarySearchForIndex {
    public static int search(int[] arr, int target){
        int min =0;
        int max = arr.length+1;
        while (min<max){
            int mid =(min+max) >>1;
            if(arr[mid] < target){
                min = mid +1;
            }else if(arr[mid] > target){
                max = mid -1;
            }else {
                return mid;
            }

        }
        // -1 代表没有找到
        return -1;
    }

    public static void main(String[] args){

        int search = search(new int[]{1, 2, 3, 4, 5, 6}, 6);
        System.out.println(search);
    }
}
