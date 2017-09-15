package com.findhappytime.javabase.tuple;


/**
 * 三个元素的元组，用于在一个方法里返回三种类型的值
 *
 * Created by zhangshu on 2017/9/14.
 */
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
    public final C third;

    public ThreeTuple(A a, B b, C c) {
        super(a, b);
        this.third = c;
    }
}
