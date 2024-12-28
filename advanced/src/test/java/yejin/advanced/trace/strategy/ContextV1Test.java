package yejin.advanced.trace.strategy;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import yejin.advanced.trace.template.code.AbstractTemplate;
import yejin.advanced.trace.template.code.SubClassLogic1;
import yejin.advanced.trace.template.code.SubClassLogic2;

@Slf4j
public class ContextV1Test {

    /**
     * 전략 패턴
     * - 변하지 않는 부분은 context 에 두고 변하는 부분을 strategy 라는 인터페이스를 만들고 해당 인터페이스를 구현하도록 해서 문제 해결
     * - 상속이 아닌 구현을 사용해서 템플릿 메서드의 문제 보완 = 상속이 아닌 위임
     * - context 는 변하지 않은 템플릿 역할을 하고 , strategy 는 변하는 알고리즘 역할
     */

    @Test
    void strategyV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
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

        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직1 실행");
            }
        };
        log.info("클래스 이름1 = {}", template1.getClass()); //TemplateMethodTest$1 -> 익명 내부 클래스 이름은 자바가 임의로 만들어 줌
        template1.execute();

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직2 실행");
            }
        };
        log.info("클래스 이름2 = {}", template2.getClass()); //TemplateMethodTest$2
        template2.execute();

    }
}
