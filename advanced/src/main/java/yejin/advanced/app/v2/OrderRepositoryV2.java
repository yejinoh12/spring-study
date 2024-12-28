package yejin.advanced.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import yejin.advanced.trace.TraceId;
import yejin.advanced.trace.TraceStatus;
import yejin.advanced.trace.hellotrace.HelloTrace2;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTrace2 trace;

    public void save(TraceId traceId, String itemId) {

        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId, "OrderRepository.save()");

            //저장 로직
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생");
            }

            //상품 저장 시 약 1초 걸린다고 가정
            sleep(1000);

            trace.end(status);
        } catch (Exception ex) {
            trace.exception(status, ex);
            throw ex;
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
