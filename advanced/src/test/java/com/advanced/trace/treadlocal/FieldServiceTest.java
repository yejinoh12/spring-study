package com.advanced.trace.treadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.advanced.trace.treadlocal.code.FieldService;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field(){

        log.info("main start");

        Runnable userA = () -> {
            fieldService.logic("userA");
        };
        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        //A가 완전히 종료 후 B를 시작
        //동시성 문제가 발생하지 않음
//        threadA.start();
//        sleep(2000);
//        threadB.start();
//        sleep(3000);

        //A가 종료하지 않고 B를 시작
        //동시성 문제 발생
        threadA.start();
        threadB.start();
        sleep(3000);

        log.info("main end");
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
