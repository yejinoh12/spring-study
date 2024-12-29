package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class TimeInvocationHandler implements InvocationHandler {

    private final Object target;

    public TimeInvocationHandler(Object target) {
        this.target = target;
    }

    //Object : 동적 프록시가 호출할 대상
    //method.invoke(target, args) 리플렉션을 사용해서  target 의 인스턴스 메서드 실행
    //args : 메서드 호출시 넘겨줄 인수

    //InvocationHandler.invoke()는 프록시 객체의 메서드가 호출될 때 자동으로 실행
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        // 동적으로 변함
        Object result = method.invoke(target, args);

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("TimeProxy 종료, resultTime={}", resultTime);
        return result;
    }
}
