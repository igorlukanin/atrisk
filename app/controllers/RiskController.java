package controllers;

import models.*;
import play.data.validation.Validation;
import play.mvc.Before;
import play.mvc.Controller;

import java.util.List;

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
        List<RiskCriterion> criteria = RiskCriterion.find("order by name").fetch();
        render(criteria);
    }
    
    public static void addCriterion(String name) {
        validation.required(name);

        if (Validation.hasErrors()) {
            params.flash();
            Validation.keep();
        }
        else {
            RiskCriterion.create(name);
        }

        context();
    }

    public static void deleteCriterion(long id) {
        RiskCriterion.delete("id = ?",id);
        context();
    }

    public static void setCriterionEvaluation(long id,boolean value) {
        RiskCriterion criterion = RiskCriterion.findById(id);

        if (null != criterion) {
            criterion.evaluation = value;
            criterion.save();
        }

        context();
    }

    public static void setCriterionImpact(long id,boolean value) {
        RiskCriterion criterion = RiskCriterion.findById(id);

        if (null != criterion) {
            criterion.impact = value;
            criterion.save();
        }

        context();
    }

    public static void setCriterionAcceptance(long id,boolean value) {
        RiskCriterion criterion = RiskCriterion.findById(id);

        if (null != criterion) {
            criterion.acceptance = value;
            criterion.save();
        }

        context();
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