package com.advanced.app.v5;

import com.advanced.trace.callback.TraceCallback;
import com.advanced.trace.callback.TraceTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate traceTemplate;

    //결국에 원본 코드를 수정해야 한다는 한계
    //원본 코드를 손대지 않고 로그 추적기를 적용하려면 프록시 개념을 알아야 함

    @GetMapping("/v5/request")
    public String request(String itemId) {
        return traceTemplate.execute("OrderController.request()", new TraceCallback<>() {
            @Override
            public String call() {
                orderService.OrderItem(itemId);
                return "ok";
            }
        });
    }
}
