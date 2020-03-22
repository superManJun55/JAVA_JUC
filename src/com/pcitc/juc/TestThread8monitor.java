package com.pcitc.juc;

/**
 * 线程8锁
 * 
 * @ClassName: TestThread8monitor
 * @Description: TODO
 * @author : chen_wenjun
 * @QQ:353376358
 * @date 2020年2月14日 下午10:28:49
 */
public class TestThread8monitor {

	public static void main(String[] args) {
		/**
		 * 1）静态方法锁是锁的Number.class（类的字节码，也是Class的实例）；一个类中不管有多少个静态方法，
		 * 	 不管访问它们的是多少个线程，这些线程都是公用同一个锁。
		 * 
		 * 2）非静态方法锁是锁的this，也就是当前实例对象。this就是指当前类的实例对象，同一个实例对象通用一个锁，不同实例对象使用不用锁。
		 */
	}

}
