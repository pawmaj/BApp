package com.luxoft.cjp.unrelated;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

import org.junit.Test;

public class SynchronizedTutor {
    static StringBuffer buf = new StringBuffer();
    static void log(String s) {
        buf.append(s+"\n");
    }

    static class TestThread implements Runnable {
        String threadName;

        public TestThread(String threadName) {
            this.threadName = threadName;
        }


        public void run() {
            for (int i=0;i<100;i++) {
                log(threadName+":"+i);
                Thread.yield();
            }
        }
    }

    @Test
    public void testThread() throws InterruptedException {
        Thread t1 =new Thread(new TestThread("t1"));
        Thread t2 = new Thread(new TestThread("t2"));
        System.out.println("Starting threads");
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished");
        System.out.println(buf);
    }

}