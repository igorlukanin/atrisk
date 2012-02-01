package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Impact extends Model {
    @ManyToOne public RiskScope scope;
    @ManyToOne(cascade = CascadeType.ALL) public SupportingAsset asset;
    @ManyToOne(cascade = CascadeType.ALL) public Threat threat;
    public int overall = 2; // 0..4
    public int recovery = 0; // 0..∞
    public int worktime = 0; // 0..∞
    public int money = 0; // 0..∞
    public int human = 2; // 0..4
    public int nature = 2; // 0..4
    public int image = 2; // 0..4

    public static Impact create(RiskScope scope,SupportingAsset asset,Threat threat) {
        Impact impact = new Impact();
        impact.scope = scope;
        impact.asset = asset;
        impact.threat = threat;
        return impact.save();
    }
}
