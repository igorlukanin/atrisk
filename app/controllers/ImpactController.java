package controllers;

import util.Parser;
import models.Impact;
import play.mvc.Controller;
import flexjson.JSONSerializer;

public class ImpactController extends Controller {
    public static void get(Long id) {
        renderJSON(new JSONSerializer().
                include("id","asset.name","threat.name","overall","recovery","worktime","money","human","nature","image").
                exclude("*").serialize(Impact.findById(id)));
    }
    
    public static void setOverall(Long id,int value) {
        try {
            Impact impact = Impact.findById(id);
            impact.overall = Parser.bound(value,0,4);
            impact.save();

            get(id);
        }
        catch (NullPointerException e) {
            error();
        }
    }
    
    public static void setRecovery(Long id,int value) {
        try {
            Impact impact = Impact.findById(id);
            impact.recovery = Parser.bound(value,0,Integer.MAX_VALUE);
            impact.save();

            get(id);
        }
        catch (NullPointerException e) {
            error();
        }
    }

    public static void setWorktime(Long id,int value) {
        try {
            Impact impact = Impact.findById(id);
            impact.worktime = Parser.bound(value,0,Integer.MAX_VALUE);
            impact.save();

            get(id);
        }
        catch (NullPointerException e) {
            error();
        }
    }

    public static void setMoney(Long id,int value) {
        try {
            Impact impact = Impact.findById(id);
            impact.money = Parser.bound(value,0,Integer.MAX_VALUE);
            impact.save();

            get(id);
        }
        catch (NullPointerException e) {
            error();
        }
    }

    public static void setHuman(Long id,int value) {
        try {
            Impact impact = Impact.findById(id);
            impact.human = Parser.bound(value,0,4);
            impact.save();

            get(id);
        }
        catch (NullPointerException e) {
            error();
        }
    }

    public static void setNature(Long id,int value) {
        try {
            Impact impact = Impact.findById(id);
            impact.nature = Parser.bound(value,0,4);
            impact.save();

            get(id);
        }
        catch (NullPointerException e) {
            error();
        }
    }

    public static void setImage(Long id,int value) {
        try {
            Impact impact = Impact.findById(id);
            impact.image = Parser.bound(value,0,4);
            impact.save();

            get(id);
        }
        catch (NullPointerException e) {
            error();
        }
    }
}
