package com.example.journal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class test {
    private static Logger Log = LoggerFactory.getLogger(test.class);

    @RequestMapping("/test")
    public int testError(){

        setThreadName("test");

        Log.info("info log");
        Log.debug("info log");

        a a = new a();
        a.a("sfdf");

        return 9 / 0;
    }

    public void setThreadName(String code){
//       同步调用ID|客户系统节点|服务系统节点||全局流水号|
        MDC.put("THREAD_ID", String.valueOf(Thread.currentThread().getId()));

        String threadName = code+"-"+String.format(new SimpleDateFormat("HHmmssS").format(new Date()), UUID.randomUUID().toString().toUpperCase().substring(0, 5));
        Thread.currentThread().setName(threadName);


    }

}
