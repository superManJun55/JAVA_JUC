package com.pcitc.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁：在完成某些操作时，址有其他线程运算全部完成，当前运算才继续执行
 * @ClassName: TestCountDownLatch 
 * @Description: TODO 
 * @author : chen_wenjun
 * @QQ:353376358
 * @date 2020年2月14日 下午2:54:53
 */
public class TestCountDownLatch {
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		final Integer count = 10;
		
		CountDownLatch latch = new CountDownLatch(count);
		LatchDemo demo = new LatchDemo(latch);
		
		for (int i = 0; i < count; i++) {
			new Thread(demo).start();
		}
		
		try {
			latch.await();//等待以上线程执行完毕
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		
		System.out.println("耗时：" + (end - start));
	}
}


class LatchDemo implements Runnable{
	
	CountDownLatch latch ;
	
	public LatchDemo(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		synchronized (this) {
			try {
				for (int i = 0; i < 50000; i++) {
					if (i % 2 == 0) {
						System.out.println(i);
					}
				}
				
			} finally {
				latch.countDown();//递减1
			}
			
		}
		
	}
	
}

