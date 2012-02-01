package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
public class PrimaryAsset extends Model {
    public enum Type {
        INFO,
        PROCESS
    }
    
    @ManyToOne public RiskScope scope;
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
