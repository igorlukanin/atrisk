package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class Risk extends Model {
    @ManyToOne
    public RiskScope scope;

    @ManyToOne
    @JoinTable(
            name = "risk_supportingasset",
            joinColumns = @JoinColumn(name = "risk_id"),
            inverseJoinColumns = @JoinColumn(name = "supportingasset_id"))
    public SupportingAsset asset;


    @ManyToOne
    @JoinTable(
            name = "risk_threat",
            joinColumns = @JoinColumn(name = "risk_id"),
            inverseJoinColumns = @JoinColumn(name = "threat_id"))
    public Threat threat;

    public int overall = 2; // 0..4
    public int recovery = 0; // 0..∞
    public int worktime = 0; // 0..∞
    public int money = 0; // 0..∞
    public int human = 2; // 0..4
    public int nature = 2; // 0..4
    public int image = 2; // 0..4

    public static Risk create(RiskScope scope,SupportingAsset asset,Threat threat) {
        Risk risk = new Risk();
        risk.scope = scope;
        risk.asset = asset;
        risk.threat = threat;
        return risk.save();
    }
}
