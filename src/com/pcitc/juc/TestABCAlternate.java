package com.pcitc.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印ABC
 * @ClassName: TestABCAlternate 
 * @Description: TODO 
 * @author : chen_wenjun
 * @QQ:353376358
 * @date 2020年2月14日 下午9:49:00
 */
public class TestABCAlternate {

	public static void main(String[] args) {

		AlternateDemo demo = new AlternateDemo();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					demo.LoopA(i);
				}
			}
		}, "A").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					demo.LoopB(i);
				}
			}
		}, "B").start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					demo.LoopC(i);
					System.out.println("---------------------------------------");
				}
			}
		}, "C").start();
	}

}

class AlternateDemo {

	private int num = 1;
	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();

	public void LoopA(int totalLoop) {

		lock.lock();

		try {

			if (num != 1) {
				try {
					condition1.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			for (int i = 0; i < 1; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
			}
			num = 2;
			condition2.signal();
		} finally {
			lock.unlock();
		}

	}

	public void LoopB(int totalLoop) {

		lock.lock();

		try {

			if (num != 2) {
				try {
					condition2.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			for (int i = 0; i < 1; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
			}
			num = 3;
			condition3.signal();
		} finally {
			lock.unlock();
		}

	}

	public void LoopC(int totalLoop) {

		lock.lock();

		try {

			if (num != 3) {
				try {
					condition3.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			for (int i = 0; i < 1; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
			}
			num = 1;
			condition1.signal();
		} finally {
			lock.unlock();
		}

	}

}