package yejin.advanced.trace.strategy;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import yejin.advanced.trace.strategy.code.strategy.ContextV2;
import yejin.advanced.trace.strategy.code.strategy.Strategy;
import yejin.advanced.trace.strategy.code.strategy.StrategyLogic1;
import yejin.advanced.trace.strategy.code.strategy.StrategyLogic2;

@Slf4j
public class ContextV2Test {

    @Test
    @DisplayName("전략을 파라미터로 전달 받는 전략 패턴")
    void strategyV1(){
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    @Test
    @DisplayName("전략 패턴 익명 내부 클래스 사용")
    void strategyV2(){
        ContextV2 context = new ContextV2();

        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });

        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }

    @Test
    @DisplayName("전략 패턴 람다 사용")
    void strategyV3(){
        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("비즈니스 로직1 실행"));
        context.execute(() -> log.info("비즈니스 로직2 실행"));
    }
}
