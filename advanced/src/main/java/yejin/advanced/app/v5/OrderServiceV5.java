package yejin.advanced.app.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yejin.advanced.trace.callback.TraceCallback;
import yejin.advanced.trace.callback.TraceTemplate;
import yejin.advanced.trace.logtrace.LogTrace;
import yejin.advanced.trace.template.AbstractTemplate;

@Service
@RequiredArgsConstructor
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate traceTemplate;

    //람다 사용
    public void OrderItem(String itemId) {
        traceTemplate.execute("OrderService.orderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });

    }
}
