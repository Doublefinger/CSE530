package app.controllers;

import org.javalite.activeweb.AppController;
import org.springframework.context.ApplicationContext;

/**
 * Created by Doublefinger on 3/22/16.
 */
public class BaseController extends AppController {
    protected ApplicationContext C() {
        return (ApplicationContext) appContext().get("spring");
    }
}
