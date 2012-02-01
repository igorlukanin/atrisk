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
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SupportingAsset extends Model {
    public enum Type {
        HARDWARE,
        SOFTWARE,
        NETWORK,
        PERSONNEL,
        SITE,
        ORGANIZATION
    }

    public String name;
    public String translatedName;
    public String icon;
    public String owner;

    @Transient
    public List<String> getIcons() {
        return Icon.getIcons(translatedName);
    }

    @Enumerated(EnumType.ORDINAL)
    public Type type;

    @ManyToMany(mappedBy = "supportingAssets",cascade = CascadeType.ALL)
    public List<PrimaryAsset> primaryAssets;

    @OneToMany(mappedBy = "asset",orphanRemoval = true)
    List<Risk> risks;

    @ManyToMany
    @JoinTable(
            name = "supportingasset_threat",
            joinColumns = @JoinColumn(name = "supportingasset_id"),
            inverseJoinColumns = @JoinColumn(name = "threat_id"))
    List<Threat> threats;

    public static SupportingAsset create(String name,Type type) {
        SupportingAsset asset = new SupportingAsset();
        asset.name = name;
        asset.translatedName = ScalarHelper.translate(name);
        asset.type = type;
        asset.primaryAssets = new ArrayList<PrimaryAsset>();
        return asset.save();
    }
}
