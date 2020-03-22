package com.pcitc.juc.test;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
/**
 * 
 * @ClassName: TestConcurrentLinkedQueue 
 * @Description: queue隊列但是先進先出 
 * @author : chen_wenjun
 * @QQ:353376358
 * @date 2020年3月3日 下午1:28:20
 */
public class TestConcurrentLinkedQueue {

	public static void main(String[] args) {
		Queue<String> queue = new ConcurrentLinkedDeque<>();
		
		for (int i = 0; i < 10; i++) {
			queue.offer("value" + i);
		}
		
		System.out.println(queue);
		System.out.println(queue.size());
		
		System.out.println(queue.peek());//
		System.out.println(queue.size());
		
		
		System.out.println(queue.poll());
		System.out.println(queue.size());
		
//		System.out.println(queue.remove("value3"));
//		System.out.println(queue.size());
	}

}
