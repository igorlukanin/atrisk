package models;

import play.db.jpa.*;
import play.mvc.*;

import javax.persistence.*;

@Entity
public class RiskScope extends Model {
    public String name;
    public boolean commercial = true;
    public String boundaries;
    
    public static RiskScope create(String name) {
        RiskScope scope = new RiskScope();
        scope.name = name;
        return scope.save();
    }
    
    public static RiskScope findBySession(Scope.Session session) {
        return findById(Long.valueOf(session.get("scope")));
    }
}
