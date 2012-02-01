package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Icon extends Model {
    public String name;
    
    public static Icon create(String name) {
        Icon icon = new Icon();
        icon.name = name;
        return icon.save();
    }
    
    public static List<String> getIcons(String name) {
        List<String> icons = new ArrayList<String>();

        try {
            String[] parts = name.toLowerCase().split(" ");

            for (String part : parts) {
                String word = part.length() > 4 ? part.substring(0,4) : part;

                for (Icon icon : Icon.find("name like ?","%" + word + "%").<Icon>fetch(10 / parts.length)) {
                    icons.add(icon.name);
                }
            }
        }
        catch (Exception e) {e.printStackTrace();}

        return(icons);
    }
}
