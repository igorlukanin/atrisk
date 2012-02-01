package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
    
    @ManyToOne
    public RiskScope scope;

    public String name;

    @Enumerated(EnumType.ORDINAL)
    public Type type;

    @ManyToMany public List<PrimaryAsset> primaryAssets;

    @OneToMany(mappedBy = "asset",orphanRemoval = true)
    List<Risk> risks;

    @ManyToMany
    @JoinTable(
            name = "supportingasset_threat",
            joinColumns = @JoinColumn(name = "supportingasset_id"),
            inverseJoinColumns = @JoinColumn(name = "threat_id"))
    List<Threat> threats;

    public static SupportingAsset create(RiskScope scope,String name,Type type) {
        SupportingAsset asset = new SupportingAsset();
        asset.scope = scope;
        asset.name = name;
        asset.type = type;
        asset.primaryAssets = new ArrayList<PrimaryAsset>();
        return asset.save();
    }
}
