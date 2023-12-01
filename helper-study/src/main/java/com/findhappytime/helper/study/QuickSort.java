package com.findhappytime.helper.study;

import java.util.Arrays;

/**
 * @author : zhangshu09
 * @date : Created in 2023/11/7 16:56
 * @description : 快速排序  快速排序是一个基于 分治 的排序方法。
 * 个人理解，
 *  1. 分治 思想， 把数组 按照基准，递归分开排序。
 *  2. 双指针的思想
 *  3. 找基准方式， 选择最左侧作为基准， ⭐️ 从右侧找到比基准小的，从左侧找到比基准大的，这样基准的位置就定下来了。
 *
 */
public class QuickSort {
    public static int[] sort(int[] sourceArray){
        return  quickSort(sourceArray, 0, sourceArray.length-1);
    }
    public static int[] quickSort(int[] arr, int left, int right){
        if(left<right){
            int partitionIndex = partition2(arr, left, right);
            quickSort(arr, left, partitionIndex-1);
            quickSort(arr, partitionIndex+1, right);
        }
        return arr;
    }


    public static void swap(int[] arr, int left, int right){
        int temp = arr[left];
        arr[left] = right;
        arr[right] = temp;
    }

    public static void main(String[] args){
        int[] sort = sort(new int[]{1,2,3,122,34,9,2,11,7,12,5});
        for (int i: sort) {
            System.out.println(i);
        }

    }


    //快速排序分区操作
    private static int partition2(int[] array, int low, int high) {
        //选择基准
        int pivot = array[low];

        //当左指针小于右指针时，重复操作
        while (low < high) {
            while (low < high && array[high] >= pivot) {
                high = high - 1;
            }
            array[low] = array[high];  // 从右向左找第一个小于x的数

            while (low < high && array[low] <= pivot) {
                low = low + 1;  // 从左向右找第一个大于x的数
            }
            array[high] = array[low];
        }
        //最后赋值基准
        array[low] = pivot;

        //返回基准所在位置，基准位置已经排序好
        return low;
    }


}
