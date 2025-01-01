package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    /**
     * JDK 동적 프록시는 인터페이스를 구현하여 프록시를 만들고,
     * Cglib 는 구체 클래스를 상속하여 프록시를 만듦
     */

    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create();
        log.info("targetClass={}", target.getClass()); //targetClass=class hello.proxy.common.service.ConcreteService
        log.info("proxyClass={}", proxy.getClass()); //proxyClass=class hello.proxy.common.service.ConcreteService$$EnhancerByCGLIB$$1c94e8ce
        proxy.call();
    }
}
