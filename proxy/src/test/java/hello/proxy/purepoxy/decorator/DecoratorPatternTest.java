package hello.proxy.purepoxy.decorator;

import hello.proxy.purepoxy.decorator.code.*;
import hello.proxy.purepoxy.decorator.code.abstractdecorator.AbstractDecorator;
import hello.proxy.purepoxy.decorator.code.abstractdecorator.MessageDecoratorV2;
import hello.proxy.purepoxy.decorator.code.abstractdecorator.TimeDecoratorV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DecoratorPatternTest {

    /**
     * 데코레이터들은 스스로 존재 할 수 없고 항상 꾸며줄 대상이 있어야함
     * 또한, 컴포넌트를 항상 호출해야함 -> 코드 중복
     */

    @Test
    @DisplayName("데코레이터 패턴 미적용")
    void noDecorator(){
        RealComponent realComponent = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
        client.execute();
    }

    @Test
    @DisplayName("데코레이터 패턴 적용V1")
    void decorator1(){
        Component realComponent = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
        client.execute();
    }

    @Test
    @DisplayName("데코레이터 패턴 적용V2 - 프록시 체인")
    void decorator2(){
        Component realComponent = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(realComponent);
        TimeDecorator timeDecorator = new TimeDecorator(messageDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);
        client.execute();
    }

    @Test
    @DisplayName("데코레이터 패턴 적용V3 - 프록시 체인, 추상 클래스 사용")
    void decorator3(){
        Component realComponent = new RealComponent();
        AbstractDecorator messageDecorator = new MessageDecoratorV2(realComponent);
        AbstractDecorator timeDecorator = new TimeDecoratorV2(messageDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);
        client.execute();
    }
}
