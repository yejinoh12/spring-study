package yejin.advanced.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import yejin.advanced.trace.strategy.code.template.Callback;
import yejin.advanced.trace.strategy.code.template.TimeLogTemplate;

@Slf4j
public class TemplateCallbackTest {

    @Test
    @DisplayName("템플릿 콜팩 패턴 - 익명 내부 클래스 사용")
    void strategyV2(){
        TimeLogTemplate timeLogTemplate = new TimeLogTemplate();

        timeLogTemplate.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });

        timeLogTemplate.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }

    @Test
    @DisplayName("템플릿 콜팩 패턴 - 람다 사용")
    void strategyV3(){
        TimeLogTemplate timeLogTemplate = new TimeLogTemplate();
        timeLogTemplate.execute(() -> log.info("비즈니스 로직1 실행"));
        timeLogTemplate.execute(() -> log.info("비즈니스 로직2 실행"));
    }


}
