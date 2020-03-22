package com.pcitc.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock:读写锁
 * 写写/读写：互斥
 * 读读：不互斥
 * 
 * @ClassName: TestReadWriteLock 
 * @Description: TODO 
 * @author : chen_wenjun
 * @QQ:353376358
 * @date 2020年2月14日 下午9:49:42
 */
public class TestReadWriteLock {

	public static void main(String[] args) {
		ReadWriteLockDemo demo = new ReadWriteLockDemo();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				double random = Math.random();
				demo.set((int) (random * 101));
			}
		}, "Write:").start();
		
		
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					demo.get();
				}
			}).start();
		}
		
	}

}


class ReadWriteLockDemo {
	
	private int num = 0;
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
	public void get() {
		lock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " num = " + num);
		} finally {
			lock.readLock().unlock();
		}
	}
	
	public void set(int num) {
		lock.writeLock().lock();
		try {
			this.num = num;
			System.out.println(Thread.currentThread().getName() + " num = " + num);
		} finally {
			lock.writeLock().unlock();
		}
	}
}