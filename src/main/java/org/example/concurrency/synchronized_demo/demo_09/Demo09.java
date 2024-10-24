package org.example.concurrency.synchronized_demo.demo_09;

import java.util.concurrent.TimeUnit;

/**
 * 程序在执行过程中，如果出现异常，默认情况锁会被释放
 * 所以，在并发处理的过程中，有异常要多加小心，不然可能会发生不一致的情况
 * 比如，在一个web app处理过程中，多个servlet线程共同访问通一个资源，这是如果异常处理不合适
 * 在第一个线程中抛出异常，其他线程就会进入同步代码去，有可能访问到异常产生的数据
 * 因此要非常小心的处理同步业务逻辑中的异常
 *
 */
public class Demo09 {

	int count = 0;
	synchronized void test(){
		System.out.println(Thread.currentThread().getName() + " start......");
		while (true) {
			count ++;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (count == 5) {
				//此处抛出异常，锁将被释放，要想不被释放，可以在这里进行catch处理，然后让循环继续
				int i = 1/0;
			}
		}
	}
	
	public static void main(String[] args) {
		Demo09 demo09 = new Demo09();
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				demo09.test();
			}
		};
		
		new Thread(r, "t1").start();
		
		try {
			new Thread(r, "t2").start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}