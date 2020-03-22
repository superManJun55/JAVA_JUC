package com.pcitc.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Lock实现的生产消费者模式，睡眠/唤醒。
 * await、signal、signalAll分别对应：Synchonized中的wait、notify、notifyAll
 * 
 * @ClassName: TestProductorAndConsumerForLock 
 * @Description: TODO 
 * @author : chen_wenjun
 * @QQ:353376358
 * @date 2020年2月14日 下午8:46:25
 */
public class TestProductorAndConsumerForLock {

	public static void main(String[] args) {

		Clerk clerk = new Clerk();
		Productor productor = new Productor(clerk);
		Consumer consumer = new Consumer(clerk);

		new Thread(productor, "生产者A").start();
		new Thread(consumer, "消费者B").start();
		new Thread(productor, "生产者C").start();
		new Thread(consumer, "消费者D").start();
	}
}

class Clerk {
	private int product = 0;

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();//获取锁

	public void purchase() {

		lock.lock();
		try {

			while (product >= 1) { // 使用while可以解决虚假唤醒的问题
				System.out.println("产品已满");

				try {
					condition.await();
				} catch (Exception e) {
				}
			}
			System.out.println(Thread.currentThread().getName() + " : " + (++product));
			condition.signalAll();
		} finally {
			lock.unlock();
		}

	}

	public void sale() {

		lock.lock();
		try {
			while (product <= 0) {

				System.out.println("缺货");
				try {
					condition.await();
				} catch (InterruptedException e) {
				}

			}
			System.out.println(Thread.currentThread().getName() + " : " + (--product));
			condition.signalAll();
		} finally {
			lock.unlock();
		}

	}
}

class Productor implements Runnable {

	private Clerk clerk;

	public Productor(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clerk.purchase();
		}
	}

}

class Consumer implements Runnable {

	private Clerk clerk;

	public Consumer(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clerk.sale();
		}
	}

}
