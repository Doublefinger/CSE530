package app.controllers;

import app.models.User;
import app.utils.PasswordHelper;
import org.apache.commons.lang.StringUtils;
import org.javalite.activeweb.annotations.POST;

/**
 * Created by Doublefinger on 4/26/16.
 */
public class LoginController extends BaseController {
    public void index(){
        render("index").layout("/layouts/login_layout");
    }

    private void register(String username, String password) {
        if(username.length() > 15 || password.length() > 15) {
            flash("message", "Username and Password cannot exceed 15 characters");
            redirect();
            return;
        }

        if(User.findFirst("username = ?", username) != null) {
            flash("message", "Username Exists");
            redirect();
            return;
        }

        User user = new User();
        String pwdSalt = PasswordHelper.generateSalt();
        user.set("username", username);
        user.set("pwd_salt", pwdSalt);
        user.set("pwd_hash", PasswordHelper.encodePassword(username, password, pwdSalt));
        user.insert();
        session("username", username);
        redirect(HomeController.class);

    }

    @POST
    public void login() {
        String username = param("username");
        String password = param("password");
        String action = param("action");

        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            flash("message", "Empty Username or Password");
            redirect();
            return;
        }

        if(StringUtils.equals(action, "register")) {
            register(username, password);
            return;
        }

        User user = User.findFirst("username = ?", username);

        if(user == null) {
            flash("message", "User does not exist");
            redirect();
            return;
        }

        String pwdSalt = user.getString("pwd_salt");
        String pwdHash = user.getString("pwd_hash");
        String loginUserHash = PasswordHelper.encodePassword(user.getString("username"), password, pwdSalt);
        if (!StringUtils.equals(pwdHash, loginUserHash)) {
            flash("message", "Wrong Password");
            redirect();
            return;
        }
        session("username", username);
        redirect(HomeController.class);
    }

    public void logout() {
        session().invalidate();
        redirect(LoginController.class);
    }
}
