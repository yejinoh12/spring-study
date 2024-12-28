package yejin.advanced.trace.treadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {

    /**
     * ThreadLocal - set, get, remove
     * - 스레드 별로 각각의 별도의 데이터 저장소를 가지게 됨
     * - 해당 스레드가 스레드 로컬을 사용하고 나면 remove 하여 저장된 값을 모두 제거해 주어야 함
     */

    private ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(String name){
        log.info("저장 name={} -> nameStore={}", name, nameStore.get());
        nameStore.set(name);
        sleep(1000);
        log.info("조회 nameStore={}", nameStore.get());
        return nameStore.get();
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
