package com.advanced.trace.hellotrace;

import com.advanced.trace.hellotrace.HelloTraceV1;
import org.junit.jupiter.api.Test;
import com.advanced.trace.TraceStatus;

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
