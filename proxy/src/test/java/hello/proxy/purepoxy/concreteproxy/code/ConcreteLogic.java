package hello.proxy.purepoxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteLogic {

    public String operation(){
        log.info("Concrete Proxy 실행");
        return "data";
    }
}
