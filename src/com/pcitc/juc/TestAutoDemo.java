package com.pcitc.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * i++问题：实际上分为三个步骤：“读，改，写”
 * 
 * AtomicInteger:
 * 	1、Volatile
 * 	2、CAS算法（Compare-And-Swap）保证数据的原子性，包括：内存值（V）、预估值（A）、更新值（B）
 * 	   当且仅当V == A时，V = B，否则，将不做任何操作
 * 
 * @ClassName: TestAutoDemo 
 * @Description: TODO 
 * @author : chen_wenjun
 * @QQ:353376358
 * @date 2020年2月14日 上午11:57:19
 */
public class TestAutoDemo {

	public static void main(String[] args) {
		AutoDemo autoDemo = new AutoDemo();
		for (int i = 1; i <= 10; i++) {
			new Thread(autoDemo).start();
		}
	}
	
}

class AutoDemo implements Runnable{
	
	private AtomicInteger num = new AtomicInteger(0);
	@Override
	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(getNum());
	}
	
	public int getNum() {
		return num.getAndIncrement();
	}
	
}