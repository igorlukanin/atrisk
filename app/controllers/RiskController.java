package controllers;

import models.*;
import play.data.validation.*;
import play.mvc.*;

public class RiskController extends Controller {
    @Before
    private static void checkAuthentication() {
        if (null == session.get("user")) {
            redirect("HomeController.root");
        }
        
        if (null == RiskScope.findBySession(session)) {
            redirect("HomeController.logout");
        }

        renderArgs.put("user",User.findBySession(session));
    }

    public static void main() {
        RiskScope scope = RiskScope.findBySession(session);
        render(scope);
    }

    public static void context() {
        RiskScope scope = RiskScope.findBySession(session);
        render(scope);
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
        idAsset();
    }

    public static void idAsset() {
        renderArgs.put("primaryInfoAssets",PrimaryAsset.find("type = ? order by name",PrimaryAsset.Type.INFO).fetch());
        renderArgs.put("primaryProcessAssets",PrimaryAsset.find("type = ? order by name",PrimaryAsset.Type.PROCESS).fetch());
        renderArgs.put("supportingAssets",SupportingAsset.find("order by name").fetch());
        renderArgs.put("supportingHardwareAssets",SupportingAsset.find("type = ? order by name",SupportingAsset.Type.HARDWARE).fetch());
        renderArgs.put("supportingSoftwareAssets",SupportingAsset.find("type = ? order by name",SupportingAsset.Type.SOFTWARE).fetch());
        renderArgs.put("supportingNetworkAssets",SupportingAsset.find("type = ? order by name",SupportingAsset.Type.NETWORK).fetch());
        renderArgs.put("supportingPersonnelAssets",SupportingAsset.find("type = ? order by name",SupportingAsset.Type.PERSONNEL).fetch());
        renderArgs.put("supportingSiteAssets",SupportingAsset.find("type = ? order by name",SupportingAsset.Type.SITE).fetch());
        renderArgs.put("supportingOrganizationAssets",SupportingAsset.find("type = ? order by name",SupportingAsset.Type.ORGANIZATION).fetch());

        render();
    }

    public static void addPrimaryAsset(String name,String type) {
        validation.required(name);
        validation.required(type);

        if (! Validation.hasErrors()) {
            for (PrimaryAsset.Type t : PrimaryAsset.Type.values()) {
                if (type.equals(t.toString())) {
                    PrimaryAsset.create(RiskScope.findBySession(session),name,t);
                    id();
                }
            }
        }

        idAsset();
    }

    public static void deletePrimaryAsset(long id) {
        PrimaryAsset.delete("id = ?",id);
        idAsset();
    }

    public static void addSupportingAsset(String name,String type) {
        validation.required(name);
        validation.required(type);

        if (! Validation.hasErrors()) {
            for (SupportingAsset.Type t : SupportingAsset.Type.values()) {
                if (type.equals(t.toString())) {
                    SupportingAsset.create(RiskScope.findBySession(session),name,t);
                    id();
                }
            }
        }

        idAsset();
    }

    public static void deleteSupportingAsset(long id) {
        SupportingAsset.delete("id = ?",id);
        idAsset();
    }

    public static void bindToPrimaryAsset(long assetId,long[] primaryAssetId) {
        SupportingAsset asset = SupportingAsset.findById(assetId);

        if (null != asset) {
            asset.primaryAssets.clear();

            if (null != primaryAssetId) {
                for (Long primaryId : primaryAssetId) {
                    PrimaryAsset primaryAsset = PrimaryAsset.findById(primaryId);

                    if (null != primaryAsset) {
                        asset.primaryAssets.add(primaryAsset);
                    }
                }
            }

            asset.save();
        }

        idAsset();
    }

    public static void idThreat() {
        renderArgs.put("threats",Threat.find("order by name").fetch());
        renderArgs.put("fmThreats",Threat.find("type = ? order by name",Threat.Type.FORCE_MAJEURE).fetch());
        renderArgs.put("orThreats",Threat.find("type = ? order by name",Threat.Type.ORGANIZATION).fetch());
        renderArgs.put("hfThreats",Threat.find("type = ? order by name",Threat.Type.HUMAN_FAILURE).fetch());
        renderArgs.put("tfThreats",Threat.find("type = ? order by name",Threat.Type.TECHNICAL_FAILURE).fetch());
        renderArgs.put("daThreats",Threat.find("type = ? order by name",Threat.Type.DELIBERATE_ACTS).fetch());
        renderArgs.put("supportingHardwareAssets",SupportingAsset.find("type = ? order by name",SupportingAsset.Type.HARDWARE).fetch());
        renderArgs.put("supportingSoftwareAssets",SupportingAsset.find("type = ? order by name",SupportingAsset.Type.SOFTWARE).fetch());
        renderArgs.put("supportingNetworkAssets",SupportingAsset.find("type = ? order by name",SupportingAsset.Type.NETWORK).fetch());
        renderArgs.put("supportingPersonnelAssets",SupportingAsset.find("type = ? order by name",SupportingAsset.Type.PERSONNEL).fetch());
        renderArgs.put("supportingSiteAssets",SupportingAsset.find("type = ? order by name",SupportingAsset.Type.SITE).fetch());
        renderArgs.put("supportingOrganizationAssets",SupportingAsset.find("type = ? order by name",SupportingAsset.Type.ORGANIZATION).fetch());

        render();
    }

    public static void addThreat(String name,String type) {
        validation.required(name);
        validation.required(type);

        if (! Validation.hasErrors()) {
            for (Threat.Type t : Threat.Type.values()) {
                if (type.equals(t.toString())) {
                    Threat.create(RiskScope.findBySession(session),name,t);
                    idThreat();
                }
            }
        }

        idThreat();
    }

    public static void deleteThreat(long id) {
        Threat.delete("id = ?",id);
        idThreat();
    }

    public static void bindToAsset(long threatId,long[] assetId) {
        Threat threat = Threat.findById(threatId);

        if (null != threat) {
            threat.assets.clear();

            if (null != assetId) {
                for (Long anId : assetId) {
                    SupportingAsset asset = SupportingAsset.findById(anId);

                    if (null != asset) {
                        threat.assets.add(asset);
                    }
                }
            }

            threat.save();
        }

        idThreat();
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