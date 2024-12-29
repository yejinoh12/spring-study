package hello.proxy.purepoxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 프록시도 실제 객체와 모양이 같아야 하기 때문에 Subject 인터페이스를 구현
 * 클라이언트 -> 프록시 호출 -> 실제 객체 호출
 * ㄴ  따라서, 프록시는 내부에 실제 객체의 참조를 가지고 있어야함
 */
@Slf4j
public class CacheProxy implements Subject {

    private Subject target;
    private String cacheValue;

    public CacheProxy(Subject target) {
        this.target = target;
    }

    /**
     * cacheValue 에 값이 있으면 실제 객체를 호출하지 않고 캐시 값을 그대로 반환
     * 처음 조회 이후에는 캐시에서 매우 빠르게 데이터를 조회할 수 있음
     */
    @Override
    public String operation() {
        log.info("프록시 호출");
        if (cacheValue == null) {
            cacheValue = target.operation();
        }
        return cacheValue;
    }
}
