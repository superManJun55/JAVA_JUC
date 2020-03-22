package com.pcitc.juc.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
/**
 * 
 * @ClassName: TestCopyOnWriteList 
 * @Description: 
 * Vector是增删改查方法都加了synchronized，保证同步，但是每个方法执行的时候都要去获得锁，
 * 性能就会大大下降，而CopyOnWriteArrayList 只是在增删改上加锁，但是读不加锁，在读方面的性能就好于Vector，
 * CopyOnWriteArrayList支持读多写少的并发情况。
 * 
 * @author : chen_wenjun
 * @QQ:353376358
 * @date 2020年3月3日 下午1:22:43
 */
public class TestCopyOnWriteList {

	public static void main(String[] args) {
//		List<Object> list = new ArrayList<>();//現場不安全
//		List<String> list = new Vector<>();//現場安全，但是效率比較低，210
		List<String> list = new CopyOnWriteArrayList<>();//CopyOnWrite并发容器用于读多写少的并发场景
		
		Random r = new Random();
		
		Thread[] arrays = new Thread[100];
		
		CountDownLatch latch = new CountDownLatch(arrays.length);
		
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < arrays.length; i++) {
			arrays[i] = new Thread(new Runnable() {
				
				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						list.add("value" + r.nextInt(10000));
					}
					
					latch.countDown();
				}
			});
		}
		
		
		for (Thread thread : arrays) {
			thread.start();
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("執行時間：" + (end -start) + "毫秒");
		System.out.println("List Size = " + list.size());
	}

}
