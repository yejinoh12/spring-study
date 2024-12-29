package hello.proxy.purepoxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

// 시간을 측정하는 부가 기능 제공
// 인터페이스가 아닌 클래스를 상속받아 만듦

@Slf4j
public class TimeProxy extends ConcreteLogic{

    //실제 호출 대상
    //프록시는 실제 호출 대상이 필요
    private ConcreteLogic concreteLogic;

    public TimeProxy(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    @Override
    public String operation(){
        log.info("TimeProxy 실행");
        long currentTimeMillis = System.currentTimeMillis();

        String result = concreteLogic.operation();

        long endTimeMillis = System.currentTimeMillis();
        log.info("TimeProxy 종료, resultTime={}ms", endTimeMillis - currentTimeMillis);
        return result;
    }
}
