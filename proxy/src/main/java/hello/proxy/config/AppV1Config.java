package hello.proxy.config;

import hello.proxy.app.v1.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * v1 : 인터페이스와 구현 클래스를 스프링 빈으로 등록
 * v2 : 인터페이스 없는 구체 클래스 스프링 빈으로 등록
 * v3 : 컴포넌트 스캔으로 스프링 빈 자동 등록
 */

@Configuration
public class AppV1Config {

    @Bean
    public OrderControllerV1 orderControllerV1(){
        return new OrderControllerV1Impl(orderServiceV1());
    }

    @Bean
    public OrderServiceV1 orderServiceV1(){
        return new OrderServiceV1Impl(orderRepositoryV1());
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(){
        return new OrderRepositoryV1Impl();
    }
}
