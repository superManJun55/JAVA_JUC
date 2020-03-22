package com.pcitc.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的方式三：实现Callable接口，相较于runnable接口，有返回值，并且有异常
 * Callable 需要FutureTask的支持，接收返回值
 * 
 * @ClassName: TestCallable 
 * @Description: TODO 
 * @author : chen_wenjun
 * @QQ:353376358
 * @date 2020年2月14日 下午3:15:57
 */
public class TestCallable {
	
	public static void main(String[] args) {
		CallableDemo demo = new CallableDemo();
		FutureTask<Integer> futureTask = new FutureTask<>(demo);
		
		new Thread(futureTask).start();
		
		try {
			Integer sum = futureTask.get();//futureTask,可用于闭锁
			System.out.println(sum);
			System.out.println("-------------------------");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
	}
}


class CallableDemo implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for (int i = 0; i <= 100000000; i++) {
			sum += i;
		}
		return sum;
	}
	
}