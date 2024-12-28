package com.advanced.app.v4;

import com.advanced.trace.logtrace.LogTrace;
import com.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final OrderServiceV4 orderService;
    private final LogTrace trace;

    @GetMapping("/v4/request")
    public String request(String itemId) {

        //익명 내부 클래스를 호출
        AbstractTemplate<String> abstractTemplate = new AbstractTemplate<>(trace) {
            @Override
            protected String call() {
                orderService.OrderItem(itemId);
                return "ok";
            }
        };

        return abstractTemplate.execute("OrderController.request()");
    }

}
