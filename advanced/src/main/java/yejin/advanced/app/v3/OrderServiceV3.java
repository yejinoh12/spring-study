package yejin.advanced.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yejin.advanced.trace.TraceStatus;
import yejin.advanced.trace.hellotrace.HelloTrace2;
import yejin.advanced.trace.logtrace.LogTrace;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void OrderItem(String itemId){
        TraceStatus status = null;
        try {
            status = trace.begin("OrderServiceV2.request");
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception ex) {
            trace.exception(status, ex);
            throw ex;
        }
    }
}
