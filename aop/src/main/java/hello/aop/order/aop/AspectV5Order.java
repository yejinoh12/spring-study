package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

//Advisor 순서를 보장하기 위해 Order 사용
//Order는 클래스 단위로 동작하기 때문에 클래스 분리
@Slf4j
public class AspectV5Order {

    @Aspect
    @Order(1)
    public static class LogAspect {
        @Around("hello.aop.order.aop.Pointcuts.allOrder()") //패키지 명, 클래스 명을 가져와야 함
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(2)
    public static class TxAspect {
        //hello.aop.order 패키지와 하위 패키지이면서 클래서 이름 패턴이 *Service
        @Around("hello.aop.order.aop.Pointcuts.orderAndService()")
        public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
            try {
                log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();
                log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
                return result;
            } catch (Exception e) {
                log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
                throw e;
            } finally {
                log.info("[리소스 릴리스] {}", joinPoint.getSignature());
            }
        }
    }
}
