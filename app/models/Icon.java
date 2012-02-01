package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Icon extends Model {
    public String name;
    
    public static Icon create(String name) {
        Icon icon = new Icon();
        icon.name = name;
        return icon.save();
    }
}
