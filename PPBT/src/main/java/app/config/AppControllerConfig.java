package app.config;

import app.ConnectionFliter;
import app.controllers.authorization.AuthorizationFilter;
import org.javalite.activeweb.AbstractControllerConfig;
import org.javalite.activeweb.AppContext;

/**
 * Created by Doublefinger on 1/4/16.
 *
 */
public class AppControllerConfig extends AbstractControllerConfig {
    @Override
    public void init(AppContext context) {
        addGlobalFilters(new AuthorizationFilter(), new ConnectionFliter());
    }
}
