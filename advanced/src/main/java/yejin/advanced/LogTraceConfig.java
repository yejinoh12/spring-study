package yejin.advanced;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yejin.advanced.trace.logtrace.FieldLogTrace;
import yejin.advanced.trace.logtrace.LogTrace;

//로그 트레이스 빈 수동 등록

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new FieldLogTrace();
    }
}
