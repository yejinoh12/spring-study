package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {

        Hello target = new Hello();

        //공통 로직1 시작
        log.info("start");
        String result1 = target.callA(); //호출 메서드만 다르고 전체 흐름 동일
        log.info("result={}", result1);

        //공통 로직2 시작
        log.info("start");
        String result2 = target.callB(); //호출 메서드만 다르고 전체 흐름 동일
        log.info("result={}", result2);

    }

    /**
     * 리플렉션
     * - 클래스나 메서드의 메타 정보를 사용해서 동적으로 호출하는 메서드를 변경할 수 있음
     * - 핵심은 클래스나 메서드 정보를 동적으로 변경할 수 있다는 점
     * - 리플렉션 기술은 런타임에 동작해서 컴파일 시점에 오류를 잡을 수 없음 주의 -> 사용 X
     */

    @Test
    void reflection1() throws Exception {

        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        // callA의 메서드 정보
        // 메서드를 호출하는 부분이 Method로 대체 됨 -> 공통 로직을 만들 수 있게 됨
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);
        log.info("result1={}", result1);

        // callB의 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result2={}", result2);

    }

    @Test
    void reflection2() throws Exception {

        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        //호출할 메서드 정보가 동적으로 제공
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        //클래스와 메서드 정보가 다르면 예외 발생
        Object result = method.invoke(target);
        log.info("result={}", result);
    }


    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
