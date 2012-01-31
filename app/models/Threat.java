package models;

import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Threat extends Model {
    public enum Type {
        FORCE_MAJEURE,
        ORGANIZATION,
        HUMAN_FAILURE,
        TECHNICAL_FAILURE,
        DELIBERATE_ACTS
    }
    
    @OneToOne public RiskScope scope;
    public String name;
    @Enumerated(EnumType.ORDINAL) public Type type;
    @ManyToMany public List<SupportingAsset> assets;
    
    public static Threat create(RiskScope scope,String name,Type type) {
        Threat threat = new Threat();
        threat.scope = scope;
        threat.name = name;
        threat.type = type;
        return threat.save();
    }
}
