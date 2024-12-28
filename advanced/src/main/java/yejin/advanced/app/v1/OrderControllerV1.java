package yejin.advanced.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yejin.advanced.trace.hellotrace.HelloTraceV1;
import yejin.advanced.trace.TraceStatus;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderControllerV1.request");
            orderService.OrderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception ex) {
            trace.exception(status, ex);
            throw ex; //예외를 던짐, 그렇지 않으면 예외를 먹어버리고 정상흐름으로 동작하게 됨, 로그는 애플리케이션 흐름에 영향을 주면 안됨
        }
    }
}
