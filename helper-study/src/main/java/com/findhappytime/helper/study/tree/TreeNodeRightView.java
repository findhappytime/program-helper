package com.findhappytime.helper.study.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author : zhangshu09
 * @date : Created in 2023/12/11 16:26
 * @description :
 */
public class TreeNodeRightView {

    public static void main(String[] args) {


    }

    public List<Integer> rightSideView(TreeNode root) {
        //res去储存最后输出的结果
        List<Integer> res = new LinkedList<>();

        if(root == null){
            return res;
        }
        //Queue去储存每一层的节点
        Queue<TreeNode> q= new LinkedList<>();
        //Queue首先加入根节点
        q.offer(root);

        //从上往下遍历
        while(!q.isEmpty()){

            int sz = q.size();

            //先记录根节点，第一层也就是根节点所在的层，遍历完后加入res中
            TreeNode last= q.peek();

            //遍历每一层
            for(int i =0; i < sz; i++){
                //这一步， 可以理解为先取出根节点
                TreeNode cur = q.poll();

                // 从右边往左遍历，如果当前的节点有右子树，就把右子树的节点放入Queue中
                if(cur.right != null){
                    q.offer(cur.right);
                }
                if(cur.left != null){
                    q.offer(cur.left);
                }
            }
            res.add(last.val);
        }
        return res;

    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
