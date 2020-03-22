package com.pcitc.juc;

public class TestProductorAndConsumer {

	public static void main(String[] args) {
		
		Clerk clerk = new Clerk();
		Productor productor = new Productor(clerk);
		Consumer consumer = new Consumer(clerk);
		
		new Thread(productor, "生产者A").start();
		new Thread(consumer, "消费者B").start();
		new Thread(productor, "生产者A1").start();
		new Thread(consumer, "消费者B1").start();
	}
}

//class Clerk {
//	private int product = 0;
//
//	public synchronized void purchase() {
//		while(product >= 5) { //使用while可以解决虚假唤醒的问题
//			System.out.println("产品已满");
//
//			try {
//				this.wait();
//			} catch (Exception e) {
//			}
//		}
//		System.out.println(Thread.currentThread().getName() + " : " + (++product));
//		this.notifyAll();
//
//	}
//
//	public synchronized void sale() {
//		while (product <= 0) {
//
//			System.out.println("缺货");
//			try {
//				this.wait();
//			} catch (InterruptedException e) {
//			}
//
//		}
//		System.out.println(Thread.currentThread().getName() + " : " + (--product));
//		this.notifyAll();
//	}
//}
//
//class Productor implements Runnable{
//	
//	private Clerk clerk;
//	
//	public Productor(Clerk clerk) {
//		this.clerk = clerk;
//	}
//
//	@Override
//	public void run() {
//		for(int i = 0; i< 20; i++) {
//			clerk.purchase();
//		}
//	}
//	
//}
//
//class Consumer implements Runnable{
//	
//	private Clerk clerk;
//	
//	public Consumer(Clerk clerk) {
//		this.clerk = clerk;
//	}
//
//	@Override
//	public void run() {
//		for(int i = 0; i< 20; i++) {
//			clerk.sale();
//		}
//	}
//	
//}
