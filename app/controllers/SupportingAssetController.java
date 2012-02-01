package controllers;

import flexjson.JSONSerializer;
import models.PrimaryAsset;
import models.SupportingAsset;
import play.data.validation.Validation;
import play.mvc.Controller;

public class SupportingAssetController extends Controller {
    public static void add(String name,String type) {
        try {
            validation.required(name);
            validation.required(type);

            if (Validation.hasErrors()) {
                throw new IllegalArgumentException(name);
            }

            for (SupportingAsset.Type t : SupportingAsset.Type.values()) {
                if (type.equals(t.toString())) {
                    SupportingAsset.create(name,t);
                    break;
                }
            }
        }
        catch (NullPointerException e) {}

        redirect("RiskController.asset");
    }

    public static void get(Long id) {
        renderJSON(new JSONSerializer().
                include("id","name","icon","icons","owner","primaryAssets.id").
                exclude("*").serialize(SupportingAsset.findById(id)));
    }

    public static void delete(long id) {
        try {
            SupportingAsset.delete("id = ?",id);
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

            SupportingAsset asset = SupportingAsset.findById(id);
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

            SupportingAsset asset = SupportingAsset.findById(id);
            asset.owner = value;
            asset.save();
        }
        catch (NullPointerException e) {
            error();
        }

        get(id);
    }
    
    public static void bind(long id,long value) {
        try {
            SupportingAsset supportingAsset = SupportingAsset.findById(id);
            PrimaryAsset primaryAsset = PrimaryAsset.findById(value);
            supportingAsset.primaryAssets.add(primaryAsset);
            primaryAsset.supportingAssets.add(supportingAsset);
            primaryAsset.save();
            supportingAsset.save();
        }
        catch (NullPointerException e) {
            error();
        }

        get(id);
    }

    public static void unbind(long id,long value) {
        try {
            SupportingAsset supportingAsset = SupportingAsset.findById(id);
            PrimaryAsset primaryAsset = PrimaryAsset.findById(value);
            supportingAsset.primaryAssets.remove(primaryAsset);
            primaryAsset.supportingAssets.remove(supportingAsset);
            primaryAsset.save();
            supportingAsset.save();
        }
        catch (NullPointerException e) {
            error();
        }

        get(id);
    }
}