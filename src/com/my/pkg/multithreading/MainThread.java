package com.my.pkg.multithreading;

import java.lang.Thread.State;
import java.util.Map;
import java.util.Map.Entry;

public class MainThread {
	public static void main(String[] args) throws InterruptedException {
		long id = Thread.currentThread().getId();
		String name = Thread.currentThread().getName();
		int priority = Thread.currentThread().getPriority();
		State state = Thread.currentThread().getState();
		String threadGroupName = Thread.currentThread().getThreadGroup().getName();

		System.out.println("id: " + id);
		System.out.println("name: " + name);
		System.out.println("priority: " + priority);
		System.out.println("state: " + state);
		System.out.println("threadGroupName: " + threadGroupName);

		new Thread(new Runnable() {

			@Override
			public void run() {
				long id = Thread.currentThread().getId();
				String name = Thread.currentThread().getName();
				int priority = Thread.currentThread().getPriority();
				State state = Thread.currentThread().getState();
				String threadGroupName = Thread.currentThread().getThreadGroup().getName();

				System.out.println("id: " + id);
				System.out.println("name: " + name);
				System.out.println("priority: " + priority);
				System.out.println("state: " + state);
				System.out.println("threadGroupName: " + threadGroupName);

				try {
					// Thread.sleep(50000);
					synchronized (this) {
						this.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}).start();

		Thread.sleep(1500);
		printResults();
		Thread.currentThread().join();
	}

	public static void printResults() {
		Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();

		for (Entry<Thread, StackTraceElement[]> entry : map.entrySet()) {
			Thread t = entry.getKey();
			StackTraceElement[] elements = entry.getValue();

			long id = t.getId();
			String name = t.getName();
			int priority = t.getPriority();
			State state = t.getState();
			ThreadGroup group = t.getThreadGroup();

			
			
			System.out.println("id: " + id);
			System.out.println("name: " + name);
			System.out.println("priority: " + priority);
			System.out.println("state: " + state);
			if (null != group) {
				System.out.println("threadGroupName: " + group.getName());
			}

			for (StackTraceElement element : elements) {
				System.out.println(element);
			}

		}

	}

}
