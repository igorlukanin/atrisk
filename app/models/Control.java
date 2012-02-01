package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Control extends Model {
    public enum Type {
        INFRASTRUCTURE,
        ORGANIZATION,
        PERSONNEL,
        HARDWARE_SOFTWARE,
        COMMUNICATIONS,
        CONTINGENCY
    }

    public String name;

    @Enumerated(EnumType.ORDINAL)
    public Type type;

    @ManyToMany
    public List<SupportingAsset> assets;

    public boolean implemented;
    public boolean effective;

    public static Control create(String name,Type type) {
        Control control = new Control();
        control.name = name;
        control.type = type;
        control.implemented = true;
        control.effective = true;
        return control.save();
    }
}
