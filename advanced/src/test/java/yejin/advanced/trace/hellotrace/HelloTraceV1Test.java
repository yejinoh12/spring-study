package yejin.advanced.trace.hellotrace;

import org.junit.jupiter.api.Test;
import yejin.advanced.trace.TraceStatus;

public class HelloTraceV1Test {

    @Test
    void begin_end(){
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("Hello");
        trace.end(status);
    }

    @Test
    void begin_exception(){
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("Hello");
        trace.exception(status, new IllegalStateException());
    }
}
