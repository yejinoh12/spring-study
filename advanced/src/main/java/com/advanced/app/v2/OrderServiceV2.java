package com.advanced.app.v2;

import com.advanced.trace.hellotrace.HelloTrace2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.advanced.trace.TraceId;
import com.advanced.trace.TraceStatus;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final HelloTrace2 trace;

    public void OrderItem(TraceId traceId, String itemId){
        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId, "OrderService.orderItem()");
            orderRepository.save(status.getTraceId(), itemId);
            trace.end(status);
        } catch (Exception ex) {
            trace.exception(status, ex);
            throw ex;
        }
    }
}
