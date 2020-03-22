package com.pcitc.juc.test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
/**
 * 
 * @ClassName: TestConcurrentMap 
 * @Description: ConcurrentMap 綫程安全的，效率比hashTable效率高一下 
 * @author : chen_wenjun
 * @QQ:353376358
 * @date 2020年3月3日 下午1:11:00
 */
public class TestConcurrentMap {

	public static void main(String[] args) {
		
//		Map<String, String> map = new Hashtable<>();//綫程安全。485毫秒
		
//		Map<String, String> map = new HashMap<>();//綫程不安全
		
		Map<String,	String> map = new ConcurrentHashMap<>();//360毫秒
		
		Random random = new Random();
		
		Thread[] threads = new Thread[100];
		
		CountDownLatch latch = new CountDownLatch(threads.length);
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Runnable() {
				
				@Override
				public void run() {
					for (int j = 0; j < 10000; j++) {
						map.put("key" + random.nextInt(100000), "value" +random.nextInt(10000));
					}
					
					latch.countDown();
				}
			});
		}
		
		for (Thread thread : threads) {
			thread.start();
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		long end = System.currentTimeMillis();
		
		System.out.println("執行時間：" + (end - start) + "毫秒");
	}

}
