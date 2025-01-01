package hello.proxy.config.v3_proxyfactory;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

public class LogTraceAdvice implements MethodInterceptor {

    public LogTraceAdvice(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    private final LogTrace logTrace;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TraceStatus status = null;
        try {
            //MethodInvocation invocation 에서 메서드에서 호출 정보를 가져옴
            Method method = invocation.getMethod();
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName();
            status = logTrace.begin(message);

            //메서드 호출

            Object result = invocation.proceed();
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            //흐름을 변경하지 않기 위해 에러를 그대로 던짐
            throw e;
        }
    }
}
