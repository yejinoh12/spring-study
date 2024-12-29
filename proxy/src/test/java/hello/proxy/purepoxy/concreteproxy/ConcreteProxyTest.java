package hello.proxy.purepoxy.concreteproxy;

import hello.proxy.purepoxy.concreteproxy.code.ConcreteClient;
import hello.proxy.purepoxy.concreteproxy.code.ConcreteLogic;
import hello.proxy.purepoxy.concreteproxy.code.TimeProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    @DisplayName("프록시 미적용")
    public void noProxy(){
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient concreteClient = new ConcreteClient(concreteLogic);
        concreteClient.execute();
    }

    @Test
    @DisplayName("프록시 적용")
    public void addProxy(){
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteLogic timeProxy = new TimeProxy(concreteLogic);
        ConcreteClient concreteClient = new ConcreteClient(timeProxy);
        concreteClient.execute();
    }
}
