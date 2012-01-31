package models;

import play.db.jpa.*;

import javax.persistence.*;

@Entity
public class Impact extends Model {
    @ManyToOne public RiskScope scope;
    @ManyToOne(cascade = CascadeType.ALL) public SupportingAsset asset;
    @ManyToOne(cascade = CascadeType.ALL) public Threat threat;
    
    public static Impact create(RiskScope scope,SupportingAsset asset,Threat threat) {
        Impact impact = new Impact();
        impact.scope = scope;
        impact.asset = asset;
        impact.threat = threat;
        return impact.save();
    }
}
