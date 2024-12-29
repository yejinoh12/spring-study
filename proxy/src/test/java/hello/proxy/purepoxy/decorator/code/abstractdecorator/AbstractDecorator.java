package hello.proxy.purepoxy.decorator.code.abstractdecorator;

import hello.proxy.purepoxy.decorator.code.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractDecorator implements Component {

    public Component component;

    public AbstractDecorator(Component component) {
        this.component = component;
    }

    public abstract String operation();

}
