package models;

import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

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
    
    @OneToOne public RiskScope scope;
    public String name;
    @Enumerated(EnumType.ORDINAL) public Type type;
    @ManyToMany public List<PrimaryAsset> primaryAssets;
    
    public static SupportingAsset create(RiskScope scope,String name,Type type) {
        SupportingAsset asset = new SupportingAsset();
        asset.scope = scope;
        asset.name = name;
        asset.type = type;
        asset.primaryAssets = new ArrayList<PrimaryAsset>();
        return asset.save();
    }
}
