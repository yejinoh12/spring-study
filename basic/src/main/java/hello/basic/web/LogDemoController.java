package hello.basic.web;

import hello.basic.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final MyLogger myLogger;

    //request scope 빈 생성을 지연
    //private final ObjectProvider<MyLogger> myLoggerProvider;
    private final LogDemoService logDemoService;

    @RequestMapping("log-demo")
    @ResponseBody
    public String legDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        //MyLogger myLogger = myLoggerProvider.getObject();
        log.info("myLogger={}", myLogger.getClass().toString());
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");
        logDemoService.logic("test Id");
        return "OK";
    }
}
