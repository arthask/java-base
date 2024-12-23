package org.example.concurrency.synchronized_demo.demo_08;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候
 * 仍然会得到该对象的锁
 * 也就是说synchronized获得的锁是可重入的
 *
 */
public class Demo08 {

	synchronized void test1(){
		System.out.println("test1 start.........");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		test2();
	}
	
	synchronized void test2(){
		System.out.println("test2 start.......");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Demo08 demo08 = new Demo08();
		demo08.test1();
	}
}