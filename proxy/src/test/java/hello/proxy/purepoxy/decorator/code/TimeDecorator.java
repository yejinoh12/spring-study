package hello.proxy.purepoxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component{

    private Component component;

    public TimeDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("타임 데코레이터 실행");
        long currentTimeMillis = System.currentTimeMillis();

        String result = component.operation();

        long endTimeMillis = System.currentTimeMillis();
        log.info("타임 데코레이터 종료, resultTime={}ms", endTimeMillis - currentTimeMillis);
        return result;
    }
}
