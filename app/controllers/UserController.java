package controllers;

import play.data.validation.Validation;
import play.mvc.Controller;

public class UserController extends Controller {
    public static void enter(String email,String password) {
        validation.required(email);
        validation.required(password);

        if (Validation.hasErrors()) {
            params.flash();
            Validation.keep();
            redirect("HomeController.root");
        }
    }

    public static void register(String username,String password) {
        System.out.println(username + " === " + password);
    }
}
