package com.pcitc.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决多线程安全问题的方式： 1、同步代码块 2、同步方法 3、同步锁（JDK1.5以后出现的） Lock
 * Lock它是显示锁，使用lock()方法上锁，必须通过unlock()方法解锁。
 * synchronized 是隐式锁
 * 
 * @ClassName: TestLock
 * @Description: TODO
 * @author : chen_wenjun
 * @QQ:353376358
 * @date 2020年2月14日 下午3:29:07
 */
public class TestLock {

	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		new Thread(ticket, "1号窗口售票").start();;
		new Thread(ticket, "2号窗口售票").start();;
		new Thread(ticket, "3号窗口售票").start();;
	}

}

class Ticket implements Runnable {

	private Lock lock = new ReentrantLock();

	private int tick = 100;

	@Override
	public void run() {

		while (true) {
			lock.lock();
			
			try {
				if (tick > 0) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " 完成售票，余票为: " + (--tick));

				}else {
					break;
				} 
			} finally {
				lock.unlock();
			}
		}

	}

}
