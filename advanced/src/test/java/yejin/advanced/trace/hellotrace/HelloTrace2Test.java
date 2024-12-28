package yejin.advanced.trace.hellotrace;

import com.advanced.trace.hellotrace.HelloTrace2;
import org.junit.jupiter.api.Test;
import com.advanced.trace.TraceStatus;

public class HelloTrace2Test {

    @Test
    void begin_end() {
        HelloTrace2 trace = new HelloTrace2();
        TraceStatus status1 = trace.begin("Hello");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "Hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception() {
        HelloTrace2 trace = new HelloTrace2();
        TraceStatus status1 = trace.begin("Hello");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "Hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}
