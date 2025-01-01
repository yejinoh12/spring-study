package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        //invocation.proceed 호출 시 타겟 클래스를 호출하고 그 결과를 받음
        //타겟 정보를 주지 않아도 프록시 생성 단계에서 타겟 정보를 받기 때문에 실행 가능
        Object result = invocation.proceed();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("TimeProxy 종료, resultTime={}", resultTime);
        return result;
    }
}
