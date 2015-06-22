package com.luxoft.cjp.misc;

import java.util.concurrent.PriorityBlockingQueue;

import org.junit.Assert;
import org.junit.Test;

public class PriorityQueueTutor {
    static StringBuffer buf = new StringBuffer();

    PriorityBlockingQueue<Order> orderQueue =
            new PriorityBlockingQueue<Order>();

    class Order implements Comparable {
        public String title;
        public boolean priority;


        public String toString() {
            return "Order " + title + ", priority=" + priority;
        }

        public Order(String title, boolean priority) {
            super();
            this.title = title;
            this.priority = priority;
        }


        public int compareTo(Object o) {
            Order or = (Order)o;
            if (or.priority==this.priority){ return 0;}
            if (or.priority==false && this.priority==true){ return 1;}
            if (or.priority==true && this.priority==false){ return -1;}
            return 9999;
        }

    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class AddOrderThread implements Runnable {

        public void run() {
            synchronized(PriorityQueueTutor.this) {
                orderQueue.put(new Order("books", false));
                sleep(10);
                orderQueue.put(new Order("table", false));
                sleep(10);
                orderQueue.put(new Order("computer", true));
                sleep(10);
                orderQueue.put(new Order("dog", false));
                notify();
            }
        }

    }

    class ReadOrderThread implements Runnable {
        int orderNum = 0;

        public void run() {
            while(orderNum<4) {
                try {
                    synchronized (PriorityQueueTutor.this) {
                        wait();
                        Order order = orderQueue.take();

                        // check that first taken order has priority==true
                        System.out.println(order.title);
                        if (order.priority && orderNum == 0) priorityAhead = true;
                        log(order.toString());
                        orderNum++;
                    }
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }


    static void log(String s) {
        buf.append(s+"\n");
    }

    boolean priorityAhead = false;

    @Test
    public void testName() throws Exception {
        Thread addOrderThread = new Thread(new AddOrderThread());
        Thread readOrderThread = new Thread(new ReadOrderThread());
        addOrderThread.start();
        /**
         * TODO: we should wait while orders will appear in the list,
         * otherwise we will read orders in order it was added to the queue
         */
        try {
            sleep(10000);
        } catch(Exception e){

            }
        readOrderThread.start();

        addOrderThread.join();
        readOrderThread.join();


        Assert.assertTrue("Order marked as priority should be the first",
                priorityAhead);
    }
}