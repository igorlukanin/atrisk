package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class RiskController extends Controller {
    @Before
    private static void checkAuthentication() {
        if (null == session.get("user")) {
            redirect("HomeController.root");
        }
    }

    public static void main() {
        render();
    }
}