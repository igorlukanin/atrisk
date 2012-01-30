package controllers;

import models.*;
import play.data.validation.*;
import play.mvc.*;

import java.util.*;

public class RiskController extends Controller {
    @Before
    private static void checkAuthentication() {
        if (null == session.get("user")) {
            redirect("HomeController.root");
        }

        renderArgs.put("user",User.findBySession(session));
    }

    public static void main() {
        RiskScope scope = RiskScope.findBySession(session);
        render(scope);
    }

    public static void context() {
        RiskScope scope = RiskScope.findBySession(session);
        List<RiskCriterion> criteria = RiskCriterion.find("order by name").fetch();
        render(scope,criteria);
    }
    
    public static void addCriterion(String name) {
        validation.required(name);

        if (Validation.hasErrors()) {
            params.flash();
            Validation.keep();
        }
        else {
            RiskCriterion.create(RiskScope.findBySession(session),name);
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

    public static void setScope(String name,String boundaries) {
        RiskScope scope = RiskScope.findBySession(session);

        if (null != scope) {
            scope.name = name;
            scope.boundaries = boundaries;
            scope.save();
        }

        context();
    }

    public static void setScopeCommercial(boolean value) {
        RiskScope scope = RiskScope.findBySession(session);

        if (null != scope) {
            scope.commercial = value;
            scope.save();
        }

        context();
    }

    public static void id() {
        List<PrimaryAsset> primaryAssets = PrimaryAsset.find("order by type desc, name").fetch();
        List<SupportingAsset> supportingAssets = SupportingAsset.find("order by type desc, name").fetch();

        render(primaryAssets,supportingAssets);
    }

    public static void addPrimaryAsset(String name) {
        validation.required(name);

        if (Validation.hasErrors()) {
            params.flash();
            Validation.keep();
        }
        else {
            PrimaryAsset.create(RiskScope.findBySession(session),name);
        }

        id();
    }

    public static void deletePrimaryAsset(long id) {
        PrimaryAsset.delete("id = ?",id);
        id();
    }

    public static void setPrimaryAssetTypeInfo(long id) {
        PrimaryAsset asset = PrimaryAsset.findById(id);

        if (null != asset) {
            asset.type = PrimaryAsset.Type.INFO;
            asset.save();
        }

        id();
    }

    public static void setPrimaryAssetTypeProcess(long id) {
        PrimaryAsset asset = PrimaryAsset.findById(id);

        if (null != asset) {
            asset.type = PrimaryAsset.Type.PROCESS;
            asset.save();
        }

        id();
    }

    public static void addSupportingAsset(String name) {
        validation.required(name);

        if (Validation.hasErrors()) {
            params.flash();
            Validation.keep();
        }
        else {
            SupportingAsset.create(RiskScope.findBySession(session),name);
        }

        id();
    }

    public static void deleteSupportingAsset(long id) {
        SupportingAsset.delete("id = ?",id);
        id();
    }

    public static void setSupportingAssetTypeHardware(long id) {
        SupportingAsset asset = SupportingAsset.findById(id);

        if (null != asset) {
            asset.type = SupportingAsset.Type.HARDWARE;
            asset.save();
        }

        id();
    }

    public static void setSupportingAssetTypeSoftware(long id) {
        SupportingAsset asset = SupportingAsset.findById(id);

        if (null != asset) {
            asset.type = SupportingAsset.Type.SOFTWARE;
            asset.save();
        }

        id();
    }

    public static void setSupportingAssetTypeNetwork(long id) {
        SupportingAsset asset = SupportingAsset.findById(id);

        if (null != asset) {
            asset.type = SupportingAsset.Type.NETWORK;
            asset.save();
        }

        id();
    }

    public static void setSupportingAssetTypePersonnel(long id) {
        SupportingAsset asset = SupportingAsset.findById(id);

        if (null != asset) {
            asset.type = SupportingAsset.Type.PERSONNEL;
            asset.save();
        }

        id();
    }

    public static void setSupportingAssetTypeSite(long id) {
        SupportingAsset asset = SupportingAsset.findById(id);

        if (null != asset) {
            asset.type = SupportingAsset.Type.SITE;
            asset.save();
        }

        id();
    }

    public static void setSupportingAssetTypeOrganization(long id) {
        SupportingAsset asset = SupportingAsset.findById(id);

        if (null != asset) {
            asset.type = SupportingAsset.Type.ORGANIZATION;
            asset.save();
        }

        id();
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