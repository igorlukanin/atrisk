package models;

import play.db.jpa.*;

import javax.persistence.*;

@Entity
public class PrimaryAsset extends Model {
    public enum Type {
        INFO,
        PROCESS
    }
    
    @OneToOne public RiskScope scope;
    public String name;
    @Enumerated(EnumType.ORDINAL) public Type type;
    
    public static PrimaryAsset create(RiskScope scope,String name,Type type) {
        PrimaryAsset asset = new PrimaryAsset();
        asset.scope = scope;
        asset.name = name;
        asset.type = type;
        return asset.save();
    }
}
