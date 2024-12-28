package yejin.advanced.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yejin.advanced.trace.TraceStatus;
import yejin.advanced.trace.logtrace.LogTrace;
import yejin.advanced.trace.template.AbstractTemplate;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepository;
    private final LogTrace trace;

    public void OrderItem(String itemId){

        //익명 내부 클래스를 호출
        //제네릭 void 반환시 Void 사용
        AbstractTemplate<Void> abstractTemplate = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                orderRepository.save(itemId);
                return null;
            }
        };

        abstractTemplate.execute("OrderService.orderItem()");
    }
}
