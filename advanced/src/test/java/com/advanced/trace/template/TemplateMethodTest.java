package com.advanced.trace.template;

import com.advanced.trace.template.code.SubClassLogic1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.advanced.trace.template.code.AbstractTemplate;
import com.advanced.trace.template.code.SubClassLogic2;

@Slf4j
public class TemplateMethodTest {

    //변하는 부분 : 비즈니스 로직
    //변하지 않는 부분 : 시간 측정
    //템플릿 메서드 패턴을 사용하여 변하는 부분과 변하지 않는 부분을 분리할 수 있다.
    @Test
    @DisplayName("템플릿 메서드 패턴 적용하지 않음")
    void templateMethodV0() {
        logic1();
        logic2();
    }

    private void logic1(){
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2(){
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    @Test
    @DisplayName("템플릿 메서드 패턴 적용")
    void templateMethodV1() {
        //비즈니스 로직 1
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();
        //비즈니스 로직 2
        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }

    @Test
    @DisplayName("익명 내부 클래스 사용")
    void templateMethodV2() {

        AbstractTemplate template1 = new AbstractTemplate(){
            @Override
            protected void call() {
                log.info("비즈니스 로직1 실행");
            }
        };
        log.info("클래스 이름1 = {}", template1.getClass()); //TemplateMethodTest$1 -> 익명 내부 클래스 이름은 자바가 임의로 만들어 줌
        template1.execute();

        AbstractTemplate template2 = new AbstractTemplate(){
            @Override
            protected void call() {
                log.info("비즈니스 로직2 실행");
            }
        };
        log.info("클래스 이름2 = {}", template2.getClass()); //TemplateMethodTest$2
        template2.execute();

    }
}
