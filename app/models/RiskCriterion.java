package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class RiskCriterion extends Model {
    public String name;
    public boolean evaluation = false;
    public boolean impact = false;
    public boolean acceptance = false;

    public static RiskCriterion create(String name) {
        RiskCriterion criterion = new RiskCriterion();
        criterion.name = name;
        return criterion.save();
    }
}
