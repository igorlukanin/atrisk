package controllers;

import flexjson.JSONSerializer;
import models.PrimaryAsset;
import play.mvc.Controller;

public class PrimaryAssetController extends Controller {
    public static void get(Long id) {
        renderJSON(new JSONSerializer().
                include("id","name","icons").
                exclude("*").serialize(PrimaryAsset.findById(id)));
    }
}