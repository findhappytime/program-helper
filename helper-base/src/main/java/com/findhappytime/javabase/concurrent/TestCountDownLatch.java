package com.findhappytime.javabase.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * ʾ����CountDownLatch��ʹ�þ���
 * Mail: ken@iamcoding.com
 *
 * @author janeky
 */
//�����Ҿ�һ���ǳ��򵥵����ӡ���������Ҫ��ӡ1-100������������Ok����1-100�Ĵ�ӡ˳��Ҫ��ͳһ��ֻ�豣֤��Ok�����������ּ��ɡ� 
//
//������������Ƕ���һ��CountDownLatch��Ȼ��10���̷ֱ߳��ӡ��n-1��*10+1����n-1��*10+10��
//���߳��е���await�����ȴ������̵߳�ִ����ϣ�ÿ���߳�ִ����Ϻ󶼵���countDown�����������await���غ��ӡ��Ok���� 

//�ܽ᣺CounDownLatch���ڹ���һ������̷߳ǳ����á�����ʾ�������о����������������ʹ���������һ���Ǽ�����Ϊ1������������״̬�����ء�
//�ڶ����Ǽ�����ΪN������ȴ�N��������ɡ���������ڱ�д���̳߳���ʱ������ʹ���������������һ������̵߳�ִ�С� 
public class TestCountDownLatch {
    private static final int N = 10;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch doneSignal = new CountDownLatch(N);
        CountDownLatch startSignal = new CountDownLatch(1);//��ʼִ���ź�  

        for (int i = 1; i <= N; i++) {
            new Thread(new Worker(i, doneSignal, startSignal)).start();//�߳�������  
        }
        System.out.println("begin------------");
        startSignal.countDown();//��ʼִ����  
        doneSignal.await();//�ȴ����е��߳�ִ�����  
        System.out.println("Ok");

    }

    static class Worker implements Runnable {
        private final CountDownLatch doneSignal;
        private final CountDownLatch startSignal;
        private int beginIndex;

        Worker(int beginIndex, CountDownLatch doneSignal,
               CountDownLatch startSignal) {
            this.startSignal = startSignal;
            this.beginIndex = beginIndex;
            this.doneSignal = doneSignal;
        }

        public void run() {
            try {
                startSignal.await(); //�ȴ���ʼִ���źŵķ���  
                beginIndex = (beginIndex - 1) * 10 + 1;
                for (int i = beginIndex; i <= beginIndex + 10; i++) {
                    System.out.println(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                doneSignal.countDown();
            }
        }
    }
}  
