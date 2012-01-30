package models;

import play.db.jpa.*;

import javax.persistence.*;

@Entity
public class RiskCriterion extends Model {
    @OneToOne public RiskScope scope;
    public String name;
    public boolean evaluation = false;
    public boolean impact = false;
    public boolean acceptance = false;

    public static RiskCriterion create(RiskScope scope,String name) {
        RiskCriterion criterion = new RiskCriterion();
        criterion.scope = scope;
        criterion.name = name;
        return criterion.save();
    }
}
