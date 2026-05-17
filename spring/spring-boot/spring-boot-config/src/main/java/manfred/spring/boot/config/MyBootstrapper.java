package manfred.spring.boot.config;

import manfred.spring.boot.config.boot.Boot;
import org.springframework.boot.BootstrapRegistry;
import org.springframework.boot.BootstrapRegistryInitializer;

public class MyBootstrapper implements BootstrapRegistryInitializer {
    @Override
    public void initialize(BootstrapRegistry registry) {
        registry.register(Boot.class, context -> new Boot());
        System.out.println("我执行了~~~~");
        new RuntimeException().printStackTrace(System.out);
    }
}
