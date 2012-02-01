package controllers;

import models.User;
import play.data.validation.Validation;
import play.i18n.Messages;
import play.mvc.Before;
import play.mvc.Controller;

public class HomeController extends Controller {
    @Before(unless = "logout")
    private static void checkAuthentication() {
        if (null != session.get("user")) {
            redirect("RiskController.main");
        }
    }

    public static void root() {
        render();
    }

    public static void login(String username,String password) {
        validation.required(username);
        validation.required(password);
        
        User user = null;

        if (! Validation.hasErrors()) {
            user = User.findByUsernameAndPassword(username,password);

            if (null == user) {
                Validation.addError("username",Messages.get("form.login.validation.wrong-credentials"));
                Validation.addError("password",Messages.get("form.login.validation.wrong-credentials"));
            }
        }

        if (Validation.hasErrors()) {
            params.flash();
            Validation.keep();
            root();
        }
        else {
            login(user);
        }
    }
    
    private static void login(User user) {
        session.put("user",user.id);
        session.put("scope",user.scopes.get(0).id);
        redirect("RiskController.main");
    }

    public static void register(String username,String password) {
        validation.required(username);
        validation.required(password);
        
        User user = null;
        
        if (! Validation.hasErrors()) {
            if (! User.checkUsernameAvailable(username)) {
                Validation.addError("username",Messages.get("form.login.validation.username-unavailable"));
            }
            else {
                user = User.create(username,password);
            }
        }

        if (Validation.hasErrors()) {
            params.flash();
            Validation.keep();
            root();
        }
        else {
            login(user);
        }
    }
    
    public static void logout() {
        session.remove("user");
        root();
    }
}