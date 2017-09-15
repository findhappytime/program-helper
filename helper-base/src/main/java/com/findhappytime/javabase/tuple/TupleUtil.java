package com.findhappytime.javabase.tuple;

import java.util.ArrayList;
import java.util.List;


/**
 * 元组辅助类，用于多种类型值的返回，如在分页的时候，后台存储过程既返回了查询得到的
 * 当页的数据（List类型），又得到了数据表中总共的数据总数（Integer类型），然后将这
 * 两个参数封装到该类中返回到action中使用
 * 使用泛型方法实现，利用参数类型推断，编译器可以找出具体的类型
 *
 * Created by zhangshu on 2017/9/14.
 */
public class TupleUtil {

    public static <A, B> TwoTuple<A, B> tuple(A a, B b) {
        return new TwoTuple<A, B>(a, b);
    }

    public static <A, B, C> ThreeTuple<A, B, C> tuple(A a, B b, C c) {
        return new ThreeTuple<A, B, C>(a, b, c);
    }

    // 测试
    public static void main(String[] args) {
        List<String> goodsBeans = new ArrayList<String>();
        for (int i = 1; i < 26; i++) {
            String goodsBean = "222";
            goodsBeans.add(goodsBean);
        }
        Integer totalProperty = 47;
//      TupleUtil<List<GoodsBean>, Integer> tupleUtil = new TupleUtil<List<GoodsBean>, Integer>(goodsBeans, totalProperty);
        TwoTuple<List<String>, Integer> twoTuple = TupleUtil.tuple(goodsBeans, totalProperty);
        List<String> list = twoTuple.first;
        System.out.println(list);
        System.out.println(twoTuple.second);
    }
}
