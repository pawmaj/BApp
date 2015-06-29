package com.luxoft.cjp.service;

import org.junit.After;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by pamajcher on 2015-06-29.
 */
public class BankFeedServiceTest {

    @Test
    public void testWriteExampleFeed() throws Exception {
        BankFeedService.writeExampleFeed();
        File f0 = new File("example/client.feed");

        assertTrue(f0.canRead());
        assertTrue(f0.canWrite());
    }

    @Test
    public void testDeleteExampleFeed() throws Exception {
        BankFeedService.writeExampleFeed();
        BankFeedService.deleteExampleFeed();
        File f0 = new File("example/client.feed");
        assertFalse(f0.exists());

    }
    @After
    public void tearDown() {
        File f0 = new File("example/client.feed");
        f0.delete();
    }
}