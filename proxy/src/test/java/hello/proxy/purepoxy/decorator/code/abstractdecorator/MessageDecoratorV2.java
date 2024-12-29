package hello.proxy.purepoxy.decorator.code.abstractdecorator;

import hello.proxy.purepoxy.decorator.code.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecoratorV2 extends AbstractDecorator{

    public MessageDecoratorV2(Component component) {
        super(component);
    }

    @Override
    public String operation() {
        log.info("메시지 데코레이터 실행");
        String result = component.operation();
        String decoResult = "*****" + result + "*****";
        log.info("데코레이터 적용 전={}, 후={}", result, decoResult);
        return decoResult;
    }

}