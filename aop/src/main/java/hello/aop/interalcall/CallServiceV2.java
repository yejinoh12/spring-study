//package hello.aop.interalcall;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class CallServiceV2 {
//
////    private final ApplicationContext applicationContext;
////
////    public CallServiceV2(ApplicationContext applicationContext) {
////        this.applicationContext = applicationContext;
////    }
//
//    private final ObjectProvider<CallServiceV2> callServiceProvider;
//
//    public CallServiceV2(ObjectProvider<CallServiceV2> callServiceProvider) {
//        this.callServiceProvider = callServiceProvider;
//    }
//
//    public void external(){
//        log.info("callServiceV2.external");
//        CallServiceV2 callServiceV2 = callServiceProvider.getObject(CallServiceV2.class);
//        callServiceV2.internal();
//    }
//
//    public void internal(){
//        log.info("call.internal");
//    }
//}
