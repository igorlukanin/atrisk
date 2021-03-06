package controllers;

import flexjson.JSONSerializer;
import models.PrimaryAsset;
import play.data.validation.Validation;
import play.mvc.Controller;
import util.ScalarHelper;

public class PrimaryAssetController extends Controller {
    public static void add(String name,String type) {
        try {
            validation.required(name);
            validation.required(type);

            if (Validation.hasErrors()) {
                throw new IllegalArgumentException(name);
            }

            for (PrimaryAsset.Type t : PrimaryAsset.Type.values()) {
                if (type.equals(t.toString())) {
                    PrimaryAsset.create(name,t);
                    break;
                }
            }
        }
        catch (NullPointerException e) {}

        redirect("RiskController.asset");
    }

    public static void list(String type) {
        try {
            validation.required(type);

            if (Validation.hasErrors()) {
                throw new IllegalArgumentException(type);
            }

            for (PrimaryAsset.Type t : PrimaryAsset.Type.values()) {
                if (type.equals(t.toString())) {
                    renderJSON(new JSONSerializer().
                            include("id","name","icon").
                            exclude("*").serialize(PrimaryAsset.find("type = ?",t).fetch()));
                    break;
                }
            }
        }
        catch (NullPointerException e) {}
    }

    public static void get(Long id) {
        renderJSON(new JSONSerializer().
                include("id","name","icon","icons","owner","criticality").
                exclude("*").serialize(PrimaryAsset.findById(id)));
    }

    public static void delete(long id) {
        try {
            PrimaryAsset asset = PrimaryAsset.findById(id);
            asset.supportingAssets = null;
            asset.delete();
        }
        catch (NullPointerException e) {
            error();
        }
    }

    public static void setIcon(Long id,String value) {
        try {
            validation.required(value);

            if (Validation.hasErrors()) {
                throw new IllegalArgumentException(value);
            }

            PrimaryAsset asset = PrimaryAsset.findById(id);
            asset.icon = value;
            asset.save();
        }
        catch (NullPointerException e) {
            error();
        }

        get(id);
    }

    public static void setOwner(Long id,String value) {
        try {
            validation.required(value);

            if (Validation.hasErrors()) {
                throw new IllegalArgumentException(value);
            }

            PrimaryAsset asset = PrimaryAsset.findById(id);
            asset.owner = value;
            asset.save();
        }
        catch (NullPointerException e) {
            error();
        }

        get(id);
    }

    public static void setCriticality(Long id,int value) {
        try {
            PrimaryAsset asset = PrimaryAsset.findById(id);
            asset.criticality = ScalarHelper.bound(value,0,4);
            asset.save();
        }
        catch (NullPointerException e) {
            error();
        }

        get(id);
    }
}