package models;

import org.apache.commons.codec.digest.DigestUtils;
import play.Play;
import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class User extends Model {
    public String username;
    public String passwordHash;
    
    public static User create(String username,String password) {
        User user = new User();
        user.username = username;
        user.passwordHash = getPasswordHash(password);
        return user.save();
    }
    
    public static boolean checkUsernameAvailable(String username) {
        return 0 == User.count("username = ?",username);
    }

    public static User findByUsernameAndPassword(String username, String password) {
        return User.find("username = ? and passwordHash = ?", username, getPasswordHash(password)).first();
    }

    private static String getPasswordHash(String password) {
        String salt = Play.configuration.getProperty("application.salt");
        return DigestUtils.sha256Hex(salt + password);
    }
}
