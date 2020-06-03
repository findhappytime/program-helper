package com.sankuai.cyhy.data.offline.query.client;

import com.sankuai.cyhy.data.processer.Getter;

/**
 * @author ：zhangshu09
 * @date ：Created in 2020-06-02 17:06
 * @description：
 */
@Getter
public class HomerTest {

    private String value;

    private String value2;

    public HomerTest(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        HomerTest app = new HomerTest("it works");
        System.out.println(app.zhaochengmingValue());

//        System.out.println("sssssss");
    }
}
