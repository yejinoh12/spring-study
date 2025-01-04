package hello.basic.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

@Slf4j
public class NetworkClient {//implements InitializingBean, DisposableBean {

    @Setter
    private String url;

    public NetworkClient() {
        log.info("생성자 호출, url={}", url);
    }

    public void connect(){
        log.info("connect = {}", url);
    }

    public void call(String msg){
        log.info("call = {} message = {}", url, msg);
    }

    public void disconnect(){
        log.info("disconnect = {}", url);
    }


    @PostConstruct
    public void init() throws Exception {
        log.info("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() throws Exception {
        log.info("NetworkClient.close");
        disconnect();
    }


//    @Override
//    public void afterPropertiesSet() throws Exception {
//        log.info("NetworkClient.afterPropertiesSet");
//
//        connect();
//        call("초기화 연결 메시지");
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        log.info("NetworkClient.destroy");
//        disconnect();
//    }
}
