package hello.basic.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
public class SingletonTest {

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        log.info("singletonBean1={}", singletonBean1);
        log.info("singletonBean2={}", singletonBean2);
        assertThat(singletonBean1).isSameAs(singletonBean2);
        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean{

        @PostConstruct
        public void init() throws Exception {
            log.info("SingletonBean.init");
        }

        @PreDestroy
        public void close() throws Exception {
            log.info("SingletonBean.close");
        }
    }
}
