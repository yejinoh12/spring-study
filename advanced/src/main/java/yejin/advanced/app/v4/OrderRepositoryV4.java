package yejin.advanced.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import yejin.advanced.trace.TraceStatus;
import yejin.advanced.trace.logtrace.LogTrace;
import yejin.advanced.trace.template.AbstractTemplate;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace trace;

    public void save(String itemId) {

        //익명 내부 클래스를 호출
        AbstractTemplate<Void> abstractTemplate = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {

                //저장 로직
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("예외 발생");
                }

                sleep(1000);

                return null;
            }
        };

        abstractTemplate.execute("OrderRepository.save()");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
