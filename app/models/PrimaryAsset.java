package models;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import play.Play;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PrimaryAsset extends Model {
    public enum Type {
        INFO,
        PROCESS
    }
    
    @ManyToOne
    public RiskScope scope;

    public String name;
    public String translatedName;

    @Enumerated(EnumType.ORDINAL)
    public Type type;

    @Transient
    public List<String> getIcons() {
        List<String> icons = new ArrayList<String>();
        
        try {
            for (String part : translatedName.toLowerCase().split(" ")) {
                List<Icon> partIcons = Icon.find("name like ?","%" + (4 < part.length() ? part.substring(0,4) : part) + "%").fetch();
                
                for (Icon icon : partIcons) {
                    icons.add(icon.name);
                }
            }
        }
        catch (Exception e) {}

        return(icons);
    }

    public static PrimaryAsset create(RiskScope scope,String name,Type type) {
        PrimaryAsset asset = new PrimaryAsset();
        asset.scope = scope;
        asset.name = name;
        asset.translatedName = createTranslatedName(name);
        asset.type = type;
        return asset.save();
    }
    
    private static String createTranslatedName(String name) {
        try {
            Translate.setKey(Play.configuration.getProperty("external.bing.api-key"));
            return Translate.execute(name,Language.AUTO_DETECT,Language.ENGLISH);
        }
        catch (Exception e) {
            return name;
        }
    }
}
