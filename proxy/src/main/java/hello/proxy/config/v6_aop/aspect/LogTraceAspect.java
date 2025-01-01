package hello.proxy.config.v6_aop.aspect;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect //애노테이션 기반 프록시 적용 시 필요
public class LogTraceAspect {

    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    //어드바이저
    @Around("execution(* hello.proxy.app..*(..))") //포인트 컷 표현식 AspectJ 표현 식 사용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{ //어드바이스 로직

        //ProceedingJoinPoint
        //MethodInvocation invocation 과 유사한 기능
        //내부에 실제 호출 대상, 전달 인자, 그리고 어떤 객체와 메서드가 호출 되었는지 정보가 포함되어 있음
        TraceStatus status = null;

//        log.info("target={}", joinPoint.getTarget()); //target=hello.proxy.app.v1.OrderControllerV1Impl@40d0a0de
//        log.info("getArgs={}", joinPoint.getArgs());  //getArgs=hello
//        log.info("getSignature={}", joinPoint.getSignature()); //getSignature=String hello.proxy.app.v1.OrderControllerV1Impl.request(String)

        try {
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            //실제 호출 대상 호출
            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            //흐름을 변경하지 않기 위해 에러를 그대로 던짐
            throw e;
        }
    }
}
