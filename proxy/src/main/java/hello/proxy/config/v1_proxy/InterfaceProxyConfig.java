package hello.proxy.config.v1_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    //구현체를 반환하는 것이 아닌 프록시 객체를 반환해야함
    //프록시를 실제 스프링 빈 대신 등록, 실제 객체는 빈으로 등록하지 않음
    //즉, 프록시를 통해 실제 객체를 호출

    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace){
        //OrderController 의 구현체는 OrderService 프록시 객체를 호출해야됨
        OrderControllerV1Impl orderControllerImpl = new OrderControllerV1Impl(orderService(logTrace));
        return new OrderControllerInterfaceProxy(orderControllerImpl, logTrace);
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace){
        OrderServiceV1Impl orderServiceImpl = new OrderServiceV1Impl(orderRepository(logTrace));
        return new OrderServiceInterfaceProxy(orderServiceImpl, logTrace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace){
        OrderRepositoryV1Impl orderRepositoryImpl = new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(orderRepositoryImpl, logTrace);
    }
}
