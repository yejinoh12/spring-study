package yejin.advanced.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yejin.advanced.trace.hellotrace.HelloTraceV1;
import yejin.advanced.trace.TraceStatus;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;
    private final HelloTraceV1 trace;

    public void OrderItem(String itemId){
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.OrderItem()");
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception ex) {
            trace.exception(status, ex);
            throw ex;
        }
    }
}
