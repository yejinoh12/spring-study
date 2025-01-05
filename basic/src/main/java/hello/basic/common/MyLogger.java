package hello.basic.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 가짜 프록시 객체는 요청이 오면 그 때 내부에서 진짜 빈을 요청하는 위임 로직이 있음
 * => 가짜 프록시 빈은 내부에 실제 MyLogger의 참조를 가지고 있기 때문에 가능
 */

@Slf4j
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;

    @Setter
    private String requestURL;

    public void log(String message) {
        log.info("[{}][{}][{}]", uuid, requestURL, message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        log.info("[{}][request scope bean create {}]", uuid, this);
    }

    @PreDestroy
    public void close() {
        log.info("[{}][{}][request scope bean close {}]", uuid, requestURL, this);
    }

}
