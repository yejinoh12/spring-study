package yejin.advanced.trace.strategy;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import yejin.advanced.trace.strategy.code.strategy.ContextV1;
import yejin.advanced.trace.strategy.code.strategy.Strategy;
import yejin.advanced.trace.strategy.code.strategy.StrategyLogic1;
import yejin.advanced.trace.strategy.code.strategy.StrategyLogic2;

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
    @DisplayName("전략을 주입 받는 방식")
    void strategyV1(){
        Strategy strategy = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategy);
        contextV1.execute();

        Strategy strategy2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(strategy2);
        contextV2.execute();
    }
}
