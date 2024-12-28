package yejin.advanced.trace.callback;

import org.springframework.stereotype.Component;
import yejin.advanced.trace.TraceStatus;
import yejin.advanced.trace.logtrace.LogTrace;

@Component
public class TraceTemplate {

    private final LogTrace trace;

    public TraceTemplate(LogTrace trace) {
        this.trace = trace;
    }

    //메세지와 콜백을 전달 받아 실행
    public <T> T execute(String message, TraceCallback<T> callback){
        TraceStatus status = null;
        try{
            status = trace.begin(message);
            T result = callback.call();
            trace.end(status);
            return result;
        }catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
