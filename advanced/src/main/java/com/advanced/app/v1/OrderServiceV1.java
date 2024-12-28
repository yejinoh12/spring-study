package com.advanced.app.v1;

import com.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.advanced.trace.TraceStatus;

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
