package com.findhappytime.helper.study;

/**
 * @author : zhangshu09
 * @date : Created in 2023/11/30 16:09
 * @description :
 *
 * 你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
 */
public class TreeConnect {

    public static void main(String[] args) {
        Node L41 = new Node(4,null,null,null);
        Node L42 = new Node(5,null,null,null);
        Node L43 = new Node(6,null,null,null);
        Node L44 = new Node(7,null,null,null);
        Node L21 = new Node(2,L41,L42,null);
        Node L22 = new Node(7,L43,L44,null);
        Node root = new Node(1,L21,L22,null);

        Node connect = connect(root);
        System.out.println("ssss");
    }

    public static Node connect(Node root){
        if(root !=null ){
            connectTwoNode(root.left, root.right);
        }
        return null;
    }


    public static void connectTwoNode(Node left, Node right){
        if(left ==null || right ==null){
            return;
        }
        left.next = right;

        // 连接各自 子节点
        connectTwoNode(left.left, left.right);
        connectTwoNode(right.left,right.right);

        // 连接相邻 子节点
        connectTwoNode(left.right,right.left);

    }




    public static class  Node{
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node(){}

        public Node(int val, Node left, Node right, Node next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }
}
