package models;

import play.db.jpa.*;

import javax.persistence.*;

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
    @Enumerated(EnumType.STRING) public Type type;
    
    public static SupportingAsset create(RiskScope scope,String name) {
        SupportingAsset asset = new SupportingAsset();
        asset.scope = scope;
        asset.name = name;
        asset.type = Type.HARDWARE;
        return asset.save();
    }
}
