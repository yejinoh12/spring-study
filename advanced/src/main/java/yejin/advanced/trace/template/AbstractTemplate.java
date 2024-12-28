package yejin.advanced.trace.template;

import yejin.advanced.trace.TraceStatus;
import yejin.advanced.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    protected AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public T execute(String message){
        TraceStatus status = null;
        try{
             status = trace.begin(message);

             // 로직 호출 - 변하는 부분
             T result = call();

             trace.end(status);

             return result;
        }catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }

    // 변하는 부분을 처리하는 메서드
    // 상속으로 구현해야 됨
    protected abstract T call();
}
