package yejin.advanced.trace.strategy.code;

import lombok.extern.slf4j.Slf4j;

/**
 *  필드에 전략을 보관하는 방식
 *   * 1) 선 조립 후 실행 방법에 적합
 *  * 2) Context 실행 시점에는 조립이 끝났기 때문에 전략을 신경쓰지 않고 단순히 실행만 하면 됨
 */

@Slf4j
public class ContextV1 {

    private Strategy strategy;

    public ContextV1(Strategy strategy){
        this.strategy = strategy;
    }

    public void execute(){
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        strategy.call(); //위임
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
