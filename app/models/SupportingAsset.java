package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SupportingAsset extends Model {
    public enum Type {
        HARDWARE,
        SOFTWARE,
        NETWORK,
        PERSONNEL,
        SITE,
        ORGANIZATION
    }
    
    @ManyToOne public RiskScope scope;
    public String name;
    @Enumerated(EnumType.ORDINAL) public Type type;
    @ManyToMany(cascade = CascadeType.ALL) public List<PrimaryAsset> primaryAssets;
    
    @OneToMany(cascade = CascadeType.ALL) List<Impact> impacts;
    @ManyToMany(cascade = CascadeType.ALL) List<Threat> threats;

    public static SupportingAsset create(RiskScope scope,String name,Type type) {
        SupportingAsset asset = new SupportingAsset();
        asset.scope = scope;
        asset.name = name;
        asset.type = type;
        asset.primaryAssets = new ArrayList<PrimaryAsset>();
        return asset.save();
    }
}
