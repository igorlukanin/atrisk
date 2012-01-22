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

    public static void context() {
        render();
    }

    public static void id() {
        render();
    }

    public static void analysis() {
        render();
    }

    public static void evaluation() {
        render();
    }

    public static void treatment() {
        render();
    }

    public static void acceptance() {
        render();
    }
}