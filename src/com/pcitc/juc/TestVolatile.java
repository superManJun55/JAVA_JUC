package com.pcitc.juc;
/**
 * Volatile关键字:当多个线程进行操作共享数据时，可以保证内存中的数据可见性
 * 				想较于Synchronized是一种轻量级的同步策略
 * 
 * 注意：
 * 1、Volatile 不具备“互斥性”
 * 2、Volatile 不能保证变量的“原子”
 * 
 * @ClassName: TestVolatile 
 * @Description: TODO 
 * @author : chen_wenjun
 * @QQ:353376358
 * @date 2020年2月14日 上午11:51:42
 */
public class TestVolatile {
	
	public static void main(String[] args) {
		
		ThreadDemo demo = new ThreadDemo();
		new Thread(demo).start();
		
		
		System.out.println("线程2执行");
		while (true) {
			if (demo.isFlag()) {
				System.out.println("--------------");
				break;
			}
		}
		
				
	}
}

class  ThreadDemo implements Runnable{
	
	private volatile boolean flag = false;

	@Override
	public void run() {
		System.out.println("线程1执行");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		flag = true;
		System.out.println("flag = " + isFlag());
	}
	
	public boolean isFlag() {
		return this.flag;
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}
