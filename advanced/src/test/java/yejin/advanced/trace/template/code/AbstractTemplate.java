package yejin.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

    /**
     * 템플릿 - 기준이 되는 거대한 툴
     * 0) 템플릿이라는 툴에 변하지 않는 부분을 몰아두고, 일부 변하는 부분을 별도로 호출하여 해결한다.
     * 1) 템플릿 메서드 패턴은 부모 클래스에 변하지 않는 템플릿 코드를 둔다.
     * 2) 변하는 부분은 자식 클래스에 두고 상속과 오버라이딩을 사용하여 처리한다.
     */

    public void execute() {
        long startTime = System.currentTimeMillis();
        //상속
        call();
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    //추상 메서드로 선언
    protected abstract void call();
}
