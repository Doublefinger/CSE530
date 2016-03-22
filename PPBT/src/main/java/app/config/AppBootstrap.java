package app.config;

import app.services.ServiceModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.javalite.activeweb.AppContext;
import org.javalite.activeweb.Bootstrap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Doublefinger on 1/4/16.
 */
public class AppBootstrap extends Bootstrap {

    @Override
    public void init(AppContext context) {
        context.set("spring", new ClassPathXmlApplicationContext("spring.xml"));
    }

    protected Injector getInjector() {
        return Guice.createInjector(new ServiceModule());
    }

    @Override
    public void destroy(AppContext context) {
        ApplicationContext spring = (ApplicationContext) context.get("spring");
        if (spring instanceof ConfigurableApplicationContext) {
            ((ConfigurableApplicationContext) spring).close();
        }
        super.destroy(context);
    }
}
