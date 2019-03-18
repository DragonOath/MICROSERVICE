package com.example.journal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Administrator
 */
public class journalTest {
    private static Logger Log = LoggerFactory.getLogger(journalTest.class);

    @org.junit.jupiter.api.Test
    public void test() {

        String threadName = String.format(new SimpleDateFormat("HHmmssS").format(new Date()),UUID.randomUUID().toString().toUpperCase().substring(0, 5));
        Thread.currentThread().setName(threadName);

        Log.info("info log");

        int i =1;
        int t = i/0;
    }

}
