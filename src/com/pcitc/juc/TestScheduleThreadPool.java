package com.pcitc.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TestScheduleThreadPool {

	public static void main(String[] args) throws Exception {
		
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
		
		List<Future<Integer>> list = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			ScheduledFuture<Integer> schedule = pool.schedule(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					int nextInt = new Random().nextInt(100);
					System.out.println(Thread.currentThread().getName() + " : " + nextInt);
					return nextInt;
				}
			}, 2, TimeUnit.SECONDS);
			
			list.add(schedule);
		}
		System.out.println("----------------------------------------");
		for (Future<Integer> future : list) {
			System.out.println(future.get());
		}
		
		pool.shutdown();
	}

}
