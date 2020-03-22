package com.pcitc.juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestCopyOnWriteArrayList {
	
	public static void main(String[] args) {
		ListThreadDemo demo = new ListThreadDemo();
		for (int i = 0; i < 10; i++) {
			new Thread(demo).start();
		}
	}
	
}


class ListThreadDemo implements Runnable{

	
//	private static List<String> list = Collections.synchronizedList(new ArrayList<>());
	private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();//适合查询，不适合增改
	
	static {
		list.add("AA");
		list.add("BB");
		list.add("CC");
	}
	
	@Override
	public void run() {
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
			list.add("DD");
		}
	}
	
}