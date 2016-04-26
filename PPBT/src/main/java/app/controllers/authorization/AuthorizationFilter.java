package app.controllers.authorization;

import org.javalite.activeweb.controller_filters.HttpSupportFilter;

/**
 * @author Igor Polevoy on 9/29/14.
 */
public class AuthorizationFilter extends HttpSupportFilter {

    @Override
    public void before() {
//        view("static_resource_version", SpringContextHolder.getBean(SiteVersionHolder.class).getVersion());
//        redirect(HomeController.class);
//
//        if (!controllerProtected()) return;
//
//        LoginInfo loginInfo = (LoginInfo) session("loginInfo");
//
//        //session out to login index
//        if (loginInfo == null) {
//            redirect(LoginController.class);
////            respond("login").status(408);
//            return;
//        }
//
//        if (controllerDirectorOnly() && !loginInfo.hasRight(RightCategory.DIRECTOR)) {
//        }
    }

//    private boolean controllerProtected() {
//        return getRoute().getController().getClass().getAnnotation(Protected.class) != null;
//    }
//
//    private boolean controllerDirectorOnly() {
//        return getRoute().getController().getClass().getAnnotation(DirectorOnly.class) != null;
//    }
}