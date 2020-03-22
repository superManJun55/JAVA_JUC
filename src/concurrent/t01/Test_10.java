/**
 * volatile关键字
 * volatile的非原子性问题
 * volatile， 只能保证可见性，不能保证原子性。
 * 不是枷锁问题，只是内存数据可见。
 */
package concurrent.t01;

import java.util.ArrayList;
import java.util.List;

public class Test_10 {
	
	volatile int count = 0;
	
	synchronized void m(){
		for(int i = 0; i < 10000; i++){
			count++;
		}
	}
	
	public static void main(String[] args) {
		final Test_10 t = new Test_10();
		List<Thread> threads = new ArrayList<>();
		for(int i = 0; i < 10; i++){
			threads.add(new Thread(new Runnable() {
				@Override
				public void run() {
					t.m();
				}
			}));
		}
		for(Thread thread : threads){
			thread.start();
		}
		for(Thread thread : threads){
			try {
				thread.join();//阻塞当前线程，直到线程结束
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(t.count);
	}
}
