package com.my.pkg.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Intro1 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService service = Executors.newFixedThreadPool(5);
		Future<Integer> fI = service.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {

				Thread.sleep(5000);

				// TODO Auto-generated method stub
				return 7;
			}
		});
		
		service.shutdown();

		System.out.println(" " + fI.get());

	}
}
