package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Threat extends Model {
    public enum Type {
        FORCE_MAJEURE,
        ORGANIZATION,
        HUMAN_FAILURE,
        TECHNICAL_FAILURE,
        DELIBERATE_ACTS
    }
    
    @ManyToOne
    public RiskScope scope;

    public String name;

    @Enumerated(EnumType.ORDINAL)
    public Type type;

    @OneToMany(mappedBy = "threat",orphanRemoval = true)
    List<Risk> risks;

    @ManyToMany(mappedBy = "threats")
    public List<SupportingAsset> assets;
    
    public static Threat create(RiskScope scope,String name,Type type) {
        Threat threat = new Threat();
        threat.scope = scope;
        threat.name = name;
        threat.type = type;
        return threat.save();
    }
}
