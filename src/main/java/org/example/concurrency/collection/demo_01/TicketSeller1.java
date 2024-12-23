package org.example.concurrency.collection.demo_01;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * 分析下面的程序可能会产生哪些问题
 * 重复销售？超量销售？
 */
public class TicketSeller1 {

    //    private static List<String> tickets = new ArrayList<>();
    //    private static Vector<String> tickets = new Vector<>();
    //    private static List<String> tickets = new LinkedList<>();
    private static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    System.out.println("销售了>>>>>" + tickets.remove(0));
                }
            }).start();
        }
    }
}