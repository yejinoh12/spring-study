package hello.basic.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Slf4j
public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);
    }

    //import org.springframework.beans.factory.ObjectProvider;
    //스프링 의존
    @Test
    void singletonClientUserPrototype(){
        //컴포넌트 스캔으로 빈 등록
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean2.class, PrototypeBean.class);

        ClientBean2 clientBean1 = ac.getBean(ClientBean2.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean2 clientBean2 = ac.getBean(ClientBean2.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    //import jakarta.inject.Provider;
    //자바 표준 기술
    @Test
    void singletonClientUserPrototype2(){
        //컴포넌트 스캔으로 빈 등록
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean3.class, PrototypeBean.class);

        ClientBean3 clientBean1 = ac.getBean(ClientBean3.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean3 clientBean2 = ac.getBean(ClientBean3.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean{

        // 생성 시점에 이미 주입이 완료 되어 버림
        private final PrototypeBean prototypeBean;

        public int logic(){
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean2{

        private final ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean3{

        private final Provider<PrototypeBean> prototypeBeanProvider;

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Getter
    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            count++;
        }

        @PostConstruct
        public void init() {
            log.info("PrototypeBean.init={}", this);
        }

        @PreDestroy
        public void destroy() {
            log.info("PrototypeBean.destroy");
        }
    }
}
