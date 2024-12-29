package hello.proxy.config;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * v1 : 인터페이스와 구현 클래스를 스프링 빈으로 등록
 * v2 : 인터페이스 없는 구체 클래스 스프링 빈으로 등록
 * v3 : 컴포넌트 스캔으로 스프링 빈 자동 등록
 */

@Configuration
public class AppV2Config {

    @Bean
    public OrderControllerV2 orderControllerV2(){
        return new OrderControllerV2(orderServiceV2());
    }

    @Bean
    public OrderServiceV2 orderServiceV2(){
        return new OrderServiceV2(orderRepositoryV2());
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(){
        return new OrderRepositoryV2();
    }
}
