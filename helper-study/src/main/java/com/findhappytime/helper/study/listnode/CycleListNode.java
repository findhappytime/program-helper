package com.findhappytime.helper.study.listnode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : zhangshu09
 * @date : Created in 2023/12/5 10:24
 * @description : 单向链表，判断是否有环
 *

 */
public class CycleListNode {


    //    方法1：最容易想到的方法是遍历所有节点，每次遍历到一个节点时，判断该节点此前是否被访问过。
    //    具体地，我们可以使用哈希表来存储所有已经访问过的节点。每次我们到达一个节点，如果该节点已经存在于哈希表中，则说明该链表是环形链表，否则就将该节点加入哈希表中。重复这一过程，直到我们遍历完整个链表即可。

    public boolean hasCycle1(ListNode head){

        Set<ListNode>  temSet = new HashSet<ListNode>();
        while (head != null){
            if(!temSet.add(head)){ // add 返回false，说明有环
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 快慢指针  又称龟兔赛跑算法
     * 我们定义两个指针，一快一慢。慢指针每次只移动一步，而快指针每次移动两步。
     * 初始时，慢指针在位置 head，而快指针在位置 head.next。
     * 这样一来，如果在移动的过程中，快指针反过来追上慢指针，就说明该链表为环形链表。
     * 否则快指针将到达链表尾部，该链表不为环形链表。
     */

    public boolean hasCycle2(ListNode head){
        if(head ==null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast){
            if(fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;

    }


}
