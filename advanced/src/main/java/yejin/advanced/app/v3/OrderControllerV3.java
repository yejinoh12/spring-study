package yejin.advanced.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yejin.advanced.trace.TraceStatus;
import yejin.advanced.trace.hellotrace.HelloTrace2;
import yejin.advanced.trace.logtrace.LogTrace;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace trace;

    @GetMapping("/v3/request")
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.OrderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception ex) {
            trace.exception(status, ex);
            throw ex; //예외를 던짐, 그렇지 않으면 예외를 먹어버리고 정상흐름으로 동작하게 됨, 로그는 애플리케이션 흐름에 영향을 주면 안됨
        }
    }
}
