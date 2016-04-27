package app.controllers.authorization;

import app.controllers.HomeController;
import app.controllers.LoginController;
import org.apache.commons.lang.StringUtils;
import org.javalite.activeweb.controller_filters.HttpSupportFilter;

/**
 * @author Igor Polevoy on 9/29/14.
 */
public class AuthorizationFilter extends HttpSupportFilter {

    @Override
    public void before() {
        String username = (String) session("username");
        if (!controllerProtected()) {
            if(username == null){
                return;
            }
            if(StringUtils.equals(getRoute().getActionName(), "logout")){
                return;
            }
            redirect(HomeController.class);
            return;
        }

        //session out to login index
        if (username == null) {
            redirect(LoginController.class);
            return;
        }
    }

    private boolean controllerProtected() {
        return getRoute().getController().getClass().getAnnotation(Protected.class) != null;
    }
}