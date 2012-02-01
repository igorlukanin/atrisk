package models;

import play.db.jpa.Model;
import util.ScalarHelper;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import java.util.List;

@Entity
public class PrimaryAsset extends Model {
    public enum Type {
        INFO,
        PROCESS
    }

    @Enumerated(EnumType.ORDINAL)
    public Type type;

    public String name;
    public String translatedName;
    public String icon;
    public String owner;
    public int criticality = 2; // 0..4

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "primaryasset_supportingasset",
            joinColumns = @JoinColumn(name = "primaryasset_id"),
            inverseJoinColumns = @JoinColumn(name = "supportingasset_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"primaryasset_id","supportingasset_id"}))
    public List<SupportingAsset> supportingAssets;

    @Transient
    public List<String> getIcons() {
        return Icon.getIcons(translatedName);
    }

    public static PrimaryAsset create(String name,Type type) {
        PrimaryAsset asset = new PrimaryAsset();
        asset.name = name;
        asset.translatedName = ScalarHelper.translate(name);
        asset.type = type;
        return asset.save();
    }
    

}
