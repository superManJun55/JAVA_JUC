package com.pcitc.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 一：线程池：提供了一个线程的队列，队列中保存着所有等待状态的线程。避免了创建与销毁额外开销，提高了响应速度
 * 二：Executor:负责线程的使用与调度的根接口
 * 		|--ExecutorService:线程池的主要接口
 * 			|--ThreadPoolExecutor:线程池的实现类
 * 			|--ScheduledExecutorService：负责线程池调度
 * 三：工具类：Executors
 * 	ExecutorService newFixedThreadPool() 创建固定大小的线程池
 *  ExecutorService newCachedThreadPool() 缓存线程池，线程池的数量不固定，可以根据需求自动改变数量
 *  ExecutorService newSingleThreadPool() 创建单个线程池
 *  
 *  ScheduleExecutorService newScheduleThreadPool()创建固定大小的线程，可以延迟或定时的执行任务
 *  
 * @ClassName: TestThreadPool 
 * @Description: TODO 
 * @author : chen_wenjun
 * @QQ:353376358
 * @date 2020年2月15日 下午1:35:48
 */
public class TestThreadPool {
	public static void main(String[] args) throws Exception {
		  
		ExecutorService fixedPool = Executors.newFixedThreadPool(5);
		
		
		Future<Integer> submit = fixedPool.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				
				int sum = 0;
				for (int i = 0; i <= 100; i++) {
					sum += i;
				}
				return sum;
			}
			
		});
		
		System.out.println(submit.get());
		
		
//		ThreadPoolDemo demo = new ThreadPoolDemo();
//		
//		for (int i = 0; i < 10; i++) {
//			fixedPool.submit(demo);
//		}
		
		fixedPool.shutdown();//等待线程执行完毕后，自行关闭
		
	}
}

class ThreadPoolDemo implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + " - " + i);
		}
	}
	
}
