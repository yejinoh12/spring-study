package hello.proxy.purepoxy.proxy;

import hello.proxy.purepoxy.proxy.code.CacheProxy;
import hello.proxy.purepoxy.proxy.code.ProxyPatternClient;
import hello.proxy.purepoxy.proxy.code.RealSubject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    @DisplayName("프록시를 적용하지 않음")
    void noProxyTest(){
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    @DisplayName("프록시 적용")
    void cacheProxyTest(){
        RealSubject realSubject = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(realSubject);
        //클라이언트는 프록시를 호출
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);
        client.execute();
        client.execute();
        client.execute();
    }
}
