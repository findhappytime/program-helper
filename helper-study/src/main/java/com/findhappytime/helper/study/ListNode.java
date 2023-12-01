package com.findhappytime.helper.study;

/**
 * @author : zhangshu09
 * @date : Created in 2023/11/29 11:05
 * @description :
 */
public class ListNode {
    int val;
    ListNode next;

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode sortOddEvenList(ListNode head){
        /*假设原链表长度为n，则时间复杂度为O(N)，空间复杂度O(N)（因merge函数递归的堆栈消耗）。
         * */
        //分割链表
        ListNode evenHead = partition(head);
        ListNode oddHead = head;

        //反转偶数位组成的链表
        evenHead = reverse(evenHead);

        //合并有序链表
        ListNode newHead = merge(oddHead,evenHead);
        return newHead;
    }
    public ListNode partition(ListNode head){
        /*按照奇数位和偶数位分割链表，返回偶数位链表的头结点
        时间O(n),空间O(1),假设传入链表长度为n
        * */
        if(head == null || head.next == null){
            return null;
        }
        //设立虚拟头结点
        ListNode oddDummyHead = new ListNode(-1,head);
        ListNode evenDummyHead = new ListNode(-1,head.next);
        //oddPre和evenPre分别指向奇数位链表和偶数位链表当前的最后一个节点
        ListNode oddPre = head;
        ListNode evenPre = head.next;
        //从cur=第三个节点开始遍历
        ListNode cur = head.next.next;
        ListNode  nextCur = null;
        //断链形成奇数位链表第一个节点，偶数位链表第一个节点
        head.next.next = null;
        head.next = null;

        int count = 3;
        //如果当前处理节点非空
        while (cur != null){
            //记录下个要处理的节点
            nextCur = cur.next;
            //当前处理节点断链
            cur.next = null;
            //如果是奇数位节点就接到奇数位链表的最后一个节点
            //如果是偶数位节点就接到偶数位链表的最后一个节点
            if(count % 2 == 1){
                oddPre.next = cur;
                oddPre = cur;
            }else{
                evenPre.next = cur;
                evenPre = cur;
            }
            //更新cur
            cur = nextCur;
            //当前节点索引+1
            count++;
        }
        //返回偶数位链表的头结点
        return evenDummyHead.next;
    }
    public ListNode reverse(ListNode head){
        /*反转以head为头结点的链表，迭代法（注意不能设立虚拟头结点）
        时间O(n),空间O(1),假设传入链表长度为n
        * */
        if(head == null || head.next == null){
            return head;
        }
        ListNode pre = null,cur = head,nextCur = null;
        while (cur != null){
            //记录下个处理节点
            nextCur = cur.next;
            //实现翻转
            cur.next = pre;
            //更新此轮已处理过的cur为pre
            pre = cur;
            //更新cur
            cur = nextCur;
        }
        //最后cur指向null，pre指向翻转后链表头结点
        return pre;
    }
    public ListNode merge(ListNode head1,ListNode head2){
        /*合并两个有序链表，递归法
        时间O(m+n),空间O(m+n)
        * */
        //边界条件&递归出口条件
        if(head1 == null || head2 == null){
            return head1 == null ? head2 : head1;
        }
        ListNode p1 = head1,p2 = head2;
        if(p1.val <= p2.val){
            p1.next = merge(p1.next,p2);
            return p1;
        }else{
            p2.next = merge(p1,p2.next);
            return p2;
        }

    }


}
