package hello.proxy.cglib.code;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * MethodInterceptor 를 구현하여 cglib 의 프록시 실행 로직을 정의
 */

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target; //프록시가 실제 호출할 대상

    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        //실제 대상을 동적으로 호출
        Object result = methodProxy.invoke(target, args);

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("TimeProxy 종료, resultTime={}", resultTime);
        return result;
    }
}
